package com.zhongtian.datascene.auth.dao.impl;

import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.dao.IUserDao;
import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.basic.dao.impl.BaseDaoHibernateImpl;

@Service("userDao")
public class UserDaoImpl extends BaseDaoHibernateImpl<UserEntity> implements IUserDao {

	@Override
	public UserEntity findUser(String username, String password) {
		String hql = "select u from UserEntity u where u.username=? and u.password=?";
		return findObject(hql, username,password);
	}

	@Override
	public UserEntity findUser(String username) {
		String hql = "select u from UserEntity u where u.username=? ";
		return findObject(hql, username);
	}

}
