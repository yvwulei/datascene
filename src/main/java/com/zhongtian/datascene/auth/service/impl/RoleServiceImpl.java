package com.zhongtian.datascene.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.dao.IRoleDao;
import com.zhongtian.datascene.auth.service.IRoleService;
import com.zhongtian.datascene.auth.vo.RoleEntity;

@Service("roleService")
public class RoleServiceImpl implements IRoleService{
	@Resource
	private IRoleDao roleDao;
	
	@Override
	public List<RoleEntity> listRolesByUserId(int userId) {
		return roleDao.listRolesByUserId(userId);
	}

}
