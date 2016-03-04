package com.zhongtian.datascene.auth.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.dao.IUserDao;
import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.basic.dao.impl.BaseDaoHibernateImpl;

@Service("userDao")
public class UserDaoImpl extends BaseDaoHibernateImpl<UserEntity> implements IUserDao {

	@Override
	public UserEntity findUser(String username, String password) {
		String hql = "select u from UserEntity u where u.username=? and u.password=?";
		return super.findObject(hql, username,password);
	}

}
