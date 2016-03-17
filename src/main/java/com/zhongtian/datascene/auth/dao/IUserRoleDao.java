package com.zhongtian.datascene.auth.dao;

import com.zhongtian.datascene.auth.vo.UserRoleEntity;
import com.zhongtian.datascene.basic.dao.IBaseDao;

public interface IUserRoleDao extends IBaseDao<UserRoleEntity> {
	public void deleteRoleUserByRid(int rid);
	public void deleteRoleUsersByUid(int uid);
}
