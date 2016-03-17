package com.zhongtian.datascene.auth.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhongtian.datascene.auth.dao.IRoleDao;
import com.zhongtian.datascene.auth.vo.RoleEntity;
import com.zhongtian.datascene.auth.vo.UserRoleEntity;
import com.zhongtian.datascene.basic.dao.impl.BaseDaoHibernateImpl;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoHibernateImpl<RoleEntity> implements IRoleDao {

	@Override
	public List<RoleEntity> listRole() {
		String hql = "from RoleEntity";
		return this.findList(hql);
	}

	@Override
	public void deleteRoleUsers(int rid) {
		
	}

	@Override
	public List<RoleEntity> listRolesByUserId(int userId) {
		String hql = "select r from RoleEntity r,UserRoleEntity ur "
				+ "where ur.rid = r.id and ur.uid = ?";
		return super.findList(hql, userId);
	}
}
