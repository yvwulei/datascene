package com.zhongtian.datascene.auth.dao;

import java.util.List;

import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.basic.dao.IBaseDao;

public interface IUserDao extends IBaseDao<UserEntity> {
	
	 /**
	  * 通过用户名和密码验证用户合法性
	  * @param username
	  * @param password
	  * @return
	  */
	 public UserEntity findUser(String username, String password);
	 
	 /**
	  * 通过用户名获取用户信息
	  * @param username
	  * @param password
	  * @return
	  */
	 public UserEntity findUser(String username);

	 /**
	  * 查找所有用户
	  * @return
	  */
	 public List<UserEntity> findAllUsers();
}
