package com.zhongtian.datascene.auth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhongtian.datascene.auth.dao.IControllerResDao;
import com.zhongtian.datascene.auth.dto.TreeDto;
import com.zhongtian.datascene.auth.vo.ControllerResEntity;
import com.zhongtian.datascene.basic.dao.impl.BaseDaoHibernateImpl;
import com.zhongtian.datascene.basic.excepiton.SysException;
import com.zhongtian.datascene.basic.util.BasicSysKitUtil;


@Repository("controllerResDao")
public class ControllerResDaoImpl extends BaseDaoHibernateImpl<ControllerResEntity> implements IControllerResDao {
	
	@Override
	public void addResources(ControllerResEntity cr, String psn) {
		ControllerResEntity p = null;
		if(!BasicSysKitUtil.isEmpty(psn)) {
			p = (ControllerResEntity)this.loadBySn(psn);
			if(p==null) throw new SysException("Controller资源对象的父节点不存在！");
			cr.setPsn(psn);
			cr.setParent(p);
		}
		ControllerResEntity self = (ControllerResEntity)this.loadBySn(cr.getSn());
		if(self==null) {
			if(cr.getOrderNum()<=0) {
				if(p==null)
					cr.setOrderNum(super.getMaxOrder(null, ControllerResEntity.class.getSimpleName())+10);
				else
					cr.setOrderNum(super.getMaxOrder(p.getId(), ControllerResEntity.class.getSimpleName())+10);
			}
			self = cr;
		} else {
			self.setClassName(cr.getClassName());
			self.setName(cr.getName());
			if(cr.getOrderNum()>0)
				self.setOrderNum(cr.getOrderNum());
			self.setSn(cr.getSn());
			self.setPsn(cr.getPsn());
			self.setParent(cr.getParent());
		}
		super.getSession().saveOrUpdate(self);
	}

	@Override
	public List<ControllerResEntity> listResByParent(Integer pid) {
		String hql = null;
		if(pid==null||pid<=0) {
			hql = getSelectHql()+" from ControllerResEntity cr where cr.parent is null order by cr.orderNum";
		} else {
			hql = getSelectHql()+" from ControllerResEntity cr where cr.parent.id="+pid+" order by cr.orderNum";
		}
		return super.findList(hql);
	}

	@Override
	public List<TreeDto> tree() {
		String sql = "select id,name,pid from t_controller_res order by order_num";
		List<TreeDto> tds = super.findListBySql(sql, TreeDto.class, false);
		return tds;
	}

	@Override
	public ControllerResEntity loadBySn(String sn) {
		String hql = "select cr from ControllerResEntity cr where cr.sn=?";
		return (ControllerResEntity)this.findObject(hql, sn);
	} 
	
	private String getSelectHql() {
		String hql = "select new ControllerResEntity(cr.id,cr.name,cr.sn,cr.psn,cr.className,cr.orderNum)";
		return hql;
	}

}
