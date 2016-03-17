package com.zhongtian.datascene.auth.dao;

import java.util.List;

import com.zhongtian.datascene.auth.vo.RoleEntity;
import com.zhongtian.datascene.basic.dao.IBaseDao;

public interface IRoleDao extends IBaseDao<RoleEntity> {
	public List<RoleEntity> listRole();
	public void deleteRoleUsers(int rid);
	
	/**
	 * 通过用户的ID来获取所有的角色
	 * @param userId
	 * @return
	 */
	public List<RoleEntity> listRolesByUserId(int userId);
	
}
