package com.zhongtian.datascene.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.dao.IRoleDao;
import com.zhongtian.datascene.auth.dao.IUserRoleDao;
import com.zhongtian.datascene.auth.service.IRoleService;
import com.zhongtian.datascene.auth.vo.RoleEntity;
import com.zhongtian.datascene.auth.vo.UserRoleEntity;

@Service("roleService")
public class RoleServiceImpl implements IRoleService{
	@Resource
	private IRoleDao roleDao;
	
	@Resource
	private IUserRoleDao userRoleDao;

	@Override
	public List<RoleEntity> selectRolesByUserId(int userId) {
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		List<UserRoleEntity> userRoles = userRoleDao.listRoles(userId);
		for(UserRoleEntity entity : userRoles){
			RoleEntity roleEntity = entity.getRole();
			roles.add(roleEntity);
		}
		return roles;
	}

}
