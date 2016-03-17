package com.zhongtian.datascene.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.dao.IUserDao;
import com.zhongtian.datascene.auth.service.IUserService;
import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.auth.web.viewvo.UserViewVO;
import com.zhongtian.datascene.basic.excepiton.SysException;
import com.zhongtian.datascene.basic.util.DateUtil;
import com.zhongtian.datascene.basic.util.ObjectUtil;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	 
	@Override
	public UserEntity addUser(UserViewVO user) {
		UserEntity ue = new UserEntity();
		try {
			ObjectUtil.copyProperties(ue, user);
			ue.setRegisterTime(DateUtil.getTime());
			ue.setStatus(1);
			String username = ue.getUsername();
			String password = ue.getPassword();
			String encStr = new Md5Hash(password, username).toHex();
			ue.setPassword(encStr);
			userDao.save(ue);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		} 
		return ue;
	}
	
	@Override
	public Boolean userExist(String username) {
		List<UserEntity> list = userDao.findByProperty("username", username);
		if(null == list || list.size() == 0)
		    return false;
		else
			return true;
	}

	@Override
	public UserEntity findUser(String username, String password) {
			String encStr = new Md5Hash(password, username).toHex();
			UserEntity user = userDao.findUser(username, encStr);
			return user;
	}

	@Override
	public UserEntity findUser(String username) {
		UserEntity userEntity = userDao.findUser(username);
		return userEntity;
	}

	@Override
	public List<UserEntity> findAllUsers() {
		return userDao.findAllUsers();
	}

}
