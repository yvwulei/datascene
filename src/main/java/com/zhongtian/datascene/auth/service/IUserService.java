package com.zhongtian.datascene.auth.service;

import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.auth.web.model.UserRegisterModel;

public interface IUserService {

	 /**
	  * 根据用户名字判断此用户是否应经被注册 true:已被注册false:未被注册
	  * @param username
	  * @return
	  */
	 public Boolean userExist(String username);
	 
	 /**
	  * 将用户注册页面信息的model对象存入数据库
	  * @param model
	  * @return
	  */
	 public UserEntity save(UserRegisterModel model);
	 
	 /**
	  * 通过用户名和密码验证用户合法性
	  * @param username
	  * @param password
	  * @return
	  */
	 public UserEntity findUser(String username, String password);
	 
	 
	 /**
	  * 通过用户名查找用户
	  * @param username
	  * @return
	  */
	 public UserEntity findUser(String username);
}
