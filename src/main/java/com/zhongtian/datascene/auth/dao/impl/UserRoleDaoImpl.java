package com.zhongtian.datascene.auth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.dao.IUserRoleDao;
import com.zhongtian.datascene.auth.vo.UserRoleEntity;
import com.zhongtian.datascene.basic.dao.impl.BaseDaoHibernateImpl;

@Service("userRoleDao")
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
	
	@Override
	public List<UserRoleEntity> listRoles(int userId) {
		String hql = "from UserRoleEntity r where r.uid = ?";
		return super.findList(hql, userId);
	}
}
