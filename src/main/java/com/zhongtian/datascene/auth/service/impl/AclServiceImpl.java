package com.zhongtian.datascene.auth.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.dao.IAclDao;
import com.zhongtian.datascene.auth.service.IAclService;
import com.zhongtian.datascene.auth.vo.AclEntity;
import com.zhongtian.datascene.auth.vo.*;

@Service("aclService")
public class AclServiceImpl implements IAclService {
	@Resource
	private IAclDao aclDao;

	@Override
	public void add(AclEntity acl) {
		aclDao.save(acl);
	}
	
	@Override
	public AclEntity loadAcl(int pid, String ptype, int rid, String rtype) {
		return aclDao.loadAcl(pid, ptype, rid, rtype);
	}

	@Override
	public AclEntity loadAclByRole(int roleId, int rid, String rtype) {
		return aclDao.loadAcl(roleId, RoleEntity.PRINCIPAL_TYPE, rid, rtype);
	}

	@Override
	public AclEntity loadAclByUser(int userId, int rid, String rtype) {
		return aclDao.loadAcl(userId, UserEntity.PRINCIPAL_TYPE, rid, rtype);
	}

	@Override
	public List<Integer> listRoleOperIdsByRes(Integer rid, String rtype,
			Integer roleId) {
		return aclDao.listRoleOperIdsByRes(rid, rtype, roleId);
	}

	@Override
	public List<Integer> listUserOperIdsByRes(Integer rid, String rtype,
			Integer userId) {
		return aclDao.listUserOperIdsByRes(rid, rtype, userId);
	}

	@Override
	public List<Integer> listUserSelfOperIdsByRes(Integer rid, String rtype,
			Integer userId) {
		return aclDao.listUserSelfOperIdsByRes(rid, rtype, userId);
	}

	@Override
	public Map<String, List<String>> listAllControllerResAndOperByRole(Integer roleId) {
		return aclDao.listAllResAndOperByRole(roleId, ControllerResEntity.RES_TYPE);
	}

	@Override
	public Map<String, List<String>> listAllControllerResAndOperByUser(Integer userId) {
		return aclDao.listAllResAndOperByUser(userId, ControllerResEntity.RES_TYPE);
	}

	@Override
	public List<String> listMenuSnByRole(Integer roleId) {
		return aclDao.listMenuSnByRole(roleId);
	}

	@Override
	public List<String> listMenuSnByUser(Integer userId) {
		return aclDao.listMenuSnByUser(userId);
	}

	@Override
	public List<Integer> listMenuIdByRole(Integer roleId) {
		return aclDao.listMenuIdByRole(roleId);
	}

	@Override
	public List<Integer> listMenuIdByUser(Integer userId) {
		return aclDao.listMenuIdByUser(userId);
	}

	@Override
	public List<Integer> listMenuIdByUserSelf(Integer userId) {
		return aclDao.listMenuIdByUserSelf(userId);
	}

}
