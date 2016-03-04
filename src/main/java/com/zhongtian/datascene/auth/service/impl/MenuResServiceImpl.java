package com.zhongtian.datascene.auth.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhongtian.datascene.auth.annotation.ModelMenu;
import com.zhongtian.datascene.auth.annotation.NavMenu;
import com.zhongtian.datascene.auth.dao.IMenuResDao;
import com.zhongtian.datascene.auth.dto.LeftMenuDto;
import com.zhongtian.datascene.auth.dto.TreeDto;
import com.zhongtian.datascene.auth.service.IMenuResService;
import com.zhongtian.datascene.auth.vo.AuthFinalVal;
import com.zhongtian.datascene.auth.vo.MenuResEntity;
import com.zhongtian.datascene.basic.util.BasicSysKitUtil;

@Service("menuResService")
public class MenuResServiceImpl implements IMenuResService {
	
	@Inject
	private IMenuResDao menuResDao;

	@Override
	public void add(MenuResEntity mr, String psn) {
		menuResDao.add(mr, psn);
	}

	@Override
	public List<MenuResEntity> listModelMenuByType(String psn, int pos) {
		return menuResDao.listModelMenuByType(psn, pos);
	}

	@Override
	public List<MenuResEntity> listTopMenu() {
		return menuResDao.listTopMenu();
	}

	@Override
	public List<LeftMenuDto> listLeftNav() {
		return menuResDao.listLeftNav();
	}

	@Override
	public List<TreeDto> tree() {
		return menuResDao.tree();
	}

	@Override
	public List<MenuResEntity> listByParent(Integer pid) {
		return menuResDao.listByParent(pid);
	}

	@Override
	public void add(MenuResEntity mr) {
		menuResDao.add(mr, mr.getPsn());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInitMenuResources(String[] packages) {
		List<Class> clzs = new ArrayList<Class>();
		List<Class> tmpClzs = null;
		for(String p:packages) {
			tmpClzs = BasicSysKitUtil.listByClass(p);
			BasicSysKitUtil.mergeList(clzs, tmpClzs);
		}
		for(Class c:clzs) {
			//添加顶部的导航菜单
			addTopNavMenu(c);
			if(c.isAnnotationPresent(NavMenu.class)) {
				//如果在类上面有NavMenu存在，就添加导航菜单和子菜单
				MenuResEntity mr = addLeftNavMenu(c);
				addModelMenu(mr,c);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addModelMenu(MenuResEntity pmr, Class c) {
		String path = null;
		if(c.isAnnotationPresent(RequestMapping.class)) {
			path = ((RequestMapping)c.getAnnotation(RequestMapping.class)).value()[0];
		}
		String cname = c.getSimpleName();
		Method[] ms = c.getDeclaredMethods();
		MenuResEntity mr = null;
		for(Method m:ms) {
			if(m.isAnnotationPresent(ModelMenu.class)) {
				String mname = m.getName();
				ModelMenu mm = m.getAnnotation(ModelMenu.class);
				RequestMapping rm = m.getAnnotation(RequestMapping.class);
				String href = path+rm.value()[0];
				String sn = getMenuSn(cname, mname, mm.sn());
				mr = menuResDao.loadBySn(sn);
				if(mr==null) mr = new MenuResEntity();
				mr.setDisplay(mm.display());
				mr.setHref(href);
				mr.setIcon(mm.icon());
				mr.setMenuPos(mm.menuPos());
				mr.setName(getModelMenuName(mname,mm));
				mr.setOrderNum(mm.orderNum());
				mr.setSn(sn);
				this.add(mr, pmr.getSn());
			}
		}
	}

	private String getModelMenuName(String mname, ModelMenu mm) {
		String name = mm.name();
		if(!BasicSysKitUtil.isEmpty(name)) return name;
		if(mname.startsWith("add")) return "添加";
		else if(mname.startsWith("update")) return "更新";
		else if(mname.startsWith("delete")) return "删除";
		else  if(mname.startsWith("show")) return "查询";
		else if(mname.startsWith("list")) return "列表";
		return "";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private MenuResEntity addLeftNavMenu(Class c) {
		String path = null;
		if(c.isAnnotationPresent(RequestMapping.class)) {
			path = ((RequestMapping)c.getAnnotation(RequestMapping.class)).value()[0];
		}
		String cname = c.getSimpleName();
		NavMenu nm = (NavMenu)c.getAnnotation(NavMenu.class);
		String sn = getMenuSn(cname, null, nm.sn());
		MenuResEntity mr = menuResDao.loadBySn(sn);
		if(mr==null) 
			mr = new MenuResEntity();
		mr.setDisplay(nm.display());
		mr.setHref(path+nm.href());
		mr.setIcon(nm.icon());
		mr.setMenuPos(nm.menuPos());
		mr.setName(nm.name());
		mr.setOrderNum(nm.orderNum());
		mr.setSn(sn);
		this.add(mr, nm.psn());
		return mr;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addTopNavMenu(Class c) {
		String path = null;
		if(c.isAnnotationPresent(RequestMapping.class)) {
			path = ((RequestMapping)c.getAnnotation(RequestMapping.class)).value()[0];
		}
		Method [] ms = c.getDeclaredMethods();
		String cname = c.getSimpleName();
		String sn = null;
		MenuResEntity mr = null;
		for(Method m:ms) {
			String mname = m.getName();
			if(m.isAnnotationPresent(NavMenu.class)) {
				path+=((RequestMapping)m.getAnnotation(RequestMapping.class)).value()[0];
				NavMenu nm = m.getAnnotation(NavMenu.class);
				sn = getMenuSn(cname,mname,nm.sn());
				mr = menuResDao.loadBySn(sn);
				if(mr==null) {
					mr = new MenuResEntity();
				}
				mr.setDisplay(nm.display());
				mr.setHref(path);
				mr.setIcon(nm.icon());
				mr.setMenuPos(AuthFinalVal.MENU_TOP_NAV);
				mr.setName(nm.name());
				mr.setOrderNum(nm.orderNum());
				mr.setSn(sn);
				this.add(mr, nm.psn());
			}
		}
	}
	
	private String getMenuSn(String cname,String mname,String asn) {
		String sn = null;
		if(BasicSysKitUtil.isEmpty(asn)) {
			if(BasicSysKitUtil.isEmpty(mname)) sn = cname;
			else sn = cname+"."+mname;
		}
		else sn = asn;
		return sn;
	}

}
