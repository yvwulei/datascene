package com.zhongtian.datascene.auth.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.annotation.AuthOper;
import com.zhongtian.datascene.auth.annotation.Res;
import com.zhongtian.datascene.auth.dao.IControllerOperDao;
import com.zhongtian.datascene.auth.dao.IControllerResDao;
import com.zhongtian.datascene.auth.dto.TreeDto;
import com.zhongtian.datascene.auth.service.IControllerResService;
import com.zhongtian.datascene.auth.vo.ControllerOperEntity;
import com.zhongtian.datascene.auth.vo.ControllerResEntity;
import com.zhongtian.datascene.basic.excepiton.SysException;
import com.zhongtian.datascene.basic.util.BasicSysKitUtil;

@Service("controllerResService")
public class ControllerResServiceImpl implements IControllerResService {
	@Resource
	private IControllerResDao controllerResDao;
	@Resource
	private IControllerOperDao controllerOperDao;
	
	@Override
	public void addResources(ControllerResEntity cr, String psn) {
		controllerResDao.addResources(cr, psn);
	}

	@Override
	public void addOper(ControllerOperEntity oper, String rsn) {
		ControllerResEntity cr = controllerResDao.loadBySn(rsn);
		if(cr==null) throw new SysException("Controller资源对象不存在！");
		ControllerOperEntity co = controllerOperDao.loadOperBySn(rsn, oper.getSn());
		if(co==null) {
			co = oper;
		} else {
			co.setIndexPos(oper.getIndexPos());
			co.setMethodName(oper.getMethodName());
			co.setName(oper.getName());
			co.setSn(oper.getSn());
		}
		co.setRid(cr.getId());
		co.setRsn(cr.getSn());
		controllerOperDao.saveOrUpdate(co);
	}

	@Override
	public void updateOper(ControllerOperEntity oper) {
		controllerOperDao.update(oper);
	}

	@Override
	public void deleteOper(int operId) {
		controllerOperDao.delete(operId);
	}

	@Override
	public List<ControllerResEntity> listResByParent(Integer pid) {
		return controllerResDao.listResByParent(pid);
	}

	@Override
	public List<ControllerOperEntity> listOperByRes(Integer rid) {
		return controllerOperDao.listOperByRes(rid);
	}

	@Override
	public ControllerOperEntity loadOperById(int operId) {
		return controllerOperDao.loadOperById(operId);
	}

	@Override
	public ControllerOperEntity loadOperBySn(String rsn, String sn) {
		return controllerOperDao.loadOperBySn(rsn, sn);
	}

	@Override
	public List<TreeDto> tree() {
		return controllerResDao.tree();
	}

	@Override
	public void add(ControllerResEntity cr) {
		controllerResDao.addResources(cr, cr.getPsn());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addInitControllerRes(String[] packages) {
		List<Class> clzs = new ArrayList<Class>();
		List<Class> tmpClzs = null;
		for(String p:packages) {
			tmpClzs = BasicSysKitUtil.listByClassAnnotation(p, Res.class);
			BasicSysKitUtil.mergeList(clzs, tmpClzs);
		}
		for(Class c:clzs) {
			ControllerResEntity cr = addResources(c);
			addAuthOper(c,cr);
		}
	}

	@SuppressWarnings({"rawtypes" })
	private void addAuthOper(Class c, ControllerResEntity cr) {
		Method [] ms = c.getDeclaredMethods();
		for(Method m:ms) {
			if(m.isAnnotationPresent(AuthOper.class)) {
				addControllerOper(m,cr);
			}
		}
	}

	private void addControllerOper(Method m, ControllerResEntity cr) {
		String mname = m.getName();
		AuthOper ao = m.getAnnotation(AuthOper.class);
		String sn = getOperSn(mname,ao);
		ControllerOperEntity co = this.loadOperBySn(cr.getSn(), sn);
		if(co==null) co = new ControllerOperEntity();
		co.setMethodName(mname);
		co.setSn(sn);
		co.setName(getOperName(mname,ao));
		co.setIndexPos(getOperIndex(mname,ao));
		this.addOper(co, cr.getSn());
	}

	private int getOperIndex(String mname, AuthOper ao) {
		if(ao.index()>=0) return ao.index();
		if(mname.startsWith("add")) return 0;
		else if(mname.startsWith("update")) return 2;
		else if(mname.startsWith("delete")) return 3;
		else return 1;
	}

	private String getOperName(String mname, AuthOper ao) {
		if(!BasicSysKitUtil.isEmpty(ao.name())) return ao.name();
		if(mname.startsWith("add")) return "添加";
		else if(mname.startsWith("update")) return "更新";
		else if(mname.startsWith("delete")) return "删除";
		else return "读取";
	}

	private String getOperSn(String mname, AuthOper ao) {
		if(!BasicSysKitUtil.isEmpty(ao.sn())) return ao.sn();
		if(mname.startsWith("add")) return "ADD";
		else if(mname.startsWith("update")) return "UPDATE";
		else if(mname.startsWith("delete")) return "DELETE";
		else return "READ";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ControllerResEntity addResources(Class c) {
		Res res = (Res)c.getAnnotation(Res.class);
		String sn = res.sn();
		ControllerResEntity cr = controllerResDao.loadBySn(sn);
		if(cr==null) cr = new ControllerResEntity();
		cr.setClassName(c.getName());
		cr.setName(res.name());
		cr.setOrderNum(res.orderNum());
		cr.setSn(sn);
		this.addResources(cr, res.psn());
		return cr;
	}

}

