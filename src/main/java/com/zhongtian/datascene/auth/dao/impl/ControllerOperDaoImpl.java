package com.zhongtian.datascene.auth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhongtian.datascene.auth.dao.IControllerOperDao;
import com.zhongtian.datascene.auth.vo.ControllerOperEntity;
import com.zhongtian.datascene.basic.dao.impl.BaseDaoHibernateImpl;

@Repository("controllerOperDao")
public class ControllerOperDaoImpl extends BaseDaoHibernateImpl<ControllerOperEntity> implements IControllerOperDao {

	@Override
	public ControllerOperEntity loadOperBySn(String rsn, String sn) {
		String hql = "from ControllerOperEntity cp where cp.rsn=? and cp.sn=?";
		return super.findObject(hql, rsn, sn);
	}

	@Override
	public List<ControllerOperEntity> listResByParent(Integer pid) {
		String hql = null;
		if(pid==null||pid<=0) {
			hql = getSelectHql()+" from ControllerResources cr where cr.parent is null order by cr.orderNum";
		} else {
			hql = getSelectHql()+" from ControllerResources cr where cr.parent.id="+pid+" order by cr.orderNum";
		}
		return super.findList(hql);
	}

	@Override
	public List<ControllerOperEntity> listOperByRes(Integer rid) {
		String hql = "selet co from ControllerOper co where co.rid=? ";
		return super.findList(hql, rid);
	}
	
	@Override
	public ControllerOperEntity loadOperById(int operId) {
		return super.findObjectById(operId);
	}
	
	private String getSelectHql() {
		String hql = "select new ControllerResources(cr.id,cr.name,cr.sn,cr.psn,cr.className,cr.orderNum)";
		return hql;
	}

}
