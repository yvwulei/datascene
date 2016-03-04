package com.zhongtian.datascene.auth.dao.impl;

import com.zhongtian.datascene.auth.dao.IUserRoleDao;
import com.zhongtian.datascene.auth.vo.UserRoleEntity;
import com.zhongtian.datascene.basic.dao.impl.BaseDaoHibernateImpl;

public class UserRoleDaoImpl extends BaseDaoHibernateImpl<UserRoleEntity> implements IUserRoleDao{

	@Override
	public void deleteRoleUserByRid(int rid) {
		String hql="delete UserRoleEntity ur where ur.role.id=?";
		this.updateByHql(hql, rid);
		
	}

	@Override
	public void deleteRoleUsersByUid(int uid) {
		String hql="delete UserRoleEntity ur where ur.user.id=?";
		this.updateByHql(hql, uid);
	}
}
