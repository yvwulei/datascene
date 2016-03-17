package com.zhongtian.datascene.auth.service;

import java.util.List;

import com.zhongtian.datascene.auth.vo.RoleEntity;

public interface IRoleService {
	
	/**
	 * 通过用户的ID标识来获取角色的集合
	 * @param userId
	 * @return
	 */
	public List<RoleEntity> listRolesByUserId(int userId);

}
