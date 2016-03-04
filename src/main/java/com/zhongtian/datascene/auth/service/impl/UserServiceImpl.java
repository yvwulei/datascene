package com.zhongtian.datascene.auth.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.dao.IUserDao;
import com.zhongtian.datascene.auth.service.IUserService;
import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.auth.web.model.UserRegisterModel;
import com.zhongtian.datascene.basic.excepiton.SysException;
import com.zhongtian.datascene.basic.util.DateUtil;
import com.zhongtian.datascene.basic.util.EncryptionUtil;
import com.zhongtian.datascene.basic.util.ObjectUtil;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	 
	@Override
	public Boolean userExist(String username) {
		if(null == userDao)
			System.out.println("userDao is null");
		else
			System.out.println("userDao is not null");
		List<UserEntity> list = userDao.findByProperty("username", username);
		if(null == list || list.size() == 0)
		    return false;
		else
			return true;
	}

	@Override
	public UserEntity save(UserRegisterModel model) {
		UserEntity entity = new UserEntity();
		try {
			ObjectUtil.copyProperties(entity, model);
			entity.setRegisterTime(DateUtil.getTime());
			entity.setStatus(1);
			String username = entity.getUsername();
			String password = entity.getPassword();
			String encStr = EncryptionUtil.md5(username+password);
			entity.setPassword(encStr);
			userDao.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		} 
		return entity;
	}

	@Override
	public UserEntity findUser(String username, String password) {
		try {
			String encStr = EncryptionUtil.md5(username+password);
			return userDao.findUser(username, encStr);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}

}
