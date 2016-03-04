package com.zhongtian.datascene.auth.dao;

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

}
