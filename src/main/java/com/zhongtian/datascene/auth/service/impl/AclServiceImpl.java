package com.zhongtian.datascene.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongtian.datascene.auth.dao.IAclDao;
import com.zhongtian.datascene.auth.service.IAclService;
import com.zhongtian.datascene.auth.vo.AclEntity;
import com.zhongtian.datascene.auth.vo.RoleEntity;
import com.zhongtian.datascene.auth.vo.UserEntity;

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
	public List<Integer> listMenuHrefByRole(Integer roleId) {
		return null;
	}

	@Override
	public List<Integer> listMenuUrlByUser(Integer userId) {
		return null;
	}


}
