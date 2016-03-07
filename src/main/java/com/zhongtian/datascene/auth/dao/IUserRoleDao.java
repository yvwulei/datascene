package com.zhongtian.datascene.auth.dao;

import java.util.List;

import com.zhongtian.datascene.auth.vo.UserRoleEntity;
import com.zhongtian.datascene.basic.dao.IBaseDao;

public interface IUserRoleDao extends IBaseDao<UserRoleEntity> {
	public void deleteRoleUserByRid(int rid);
	public void deleteRoleUsersByUid(int uid);
	/**
	 * 通过用户的ID来获取所有的角色
	 * @param userId
	 * @return
	 */
	public List<UserRoleEntity> listRoles(int userId);
}
