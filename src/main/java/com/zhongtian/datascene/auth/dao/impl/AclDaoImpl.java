package com.zhongtian.datascene.auth.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhongtian.datascene.auth.dao.IAclDao;
import com.zhongtian.datascene.auth.vo.AclEntity;
import com.zhongtian.datascene.auth.vo.MenuResEntity;
import com.zhongtian.datascene.auth.vo.RoleEntity;
import com.zhongtian.datascene.auth.vo.UserEntity;
import com.zhongtian.datascene.basic.dao.impl.BaseDaoHibernateImpl;
import com.zhongtian.datascene.basic.util.BasicSysKitUtil;

@Repository("aclDao")
public class AclDaoImpl extends BaseDaoHibernateImpl<AclEntity> implements IAclDao {

	@Override
	public AclEntity loadAcl(int sid, String stype, int rid, String rtype) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sid", sid);
		params.put("stype", stype);
		params.put("rid", rid);
		params.put("rtype", rtype);
		return (AclEntity)super.findByProperty(params).get(0);
	}
	
	@Override
	public List<String> listMenuSnByRole(Integer roleId) {
		return listMenuSnByPrin(roleId,RoleEntity.PRINCIPAL_TYPE);
	}
	
	@Override
	public List<String> listMenuSnByUser(Integer userId) {
		List<String> usns = listMenuSnByPrin(userId,UserEntity.PRINCIPAL_TYPE);
		String hql = "select a.aclState from " +
				"AclEntity a,MenuResEntity mr,UserRoleEntity ur where a.rid=mr.id and ur.role.id=a.sid and a.aclState=1 and ur.user.id=? and a.stype=? and a.rtype=?";
		List<String> rsns = super.findList(hql, userId,RoleEntity.PRINCIPAL_TYPE,MenuResEntity.RES_TYPE);
		BasicSysKitUtil.mergeList(usns, rsns);
		return usns;
	}
	
	@Override
	public List<Integer> listMenuIdByRole(Integer roleId) {
		return listMenuIdByPrin(roleId,RoleEntity.PRINCIPAL_TYPE);
	}
	
	@Override
	public List<Integer> listMenuIdByUser(Integer userId) {
		List<Integer> uids = listMenuIdByPrin(userId,UserEntity.PRINCIPAL_TYPE);
		String hql = "select mr.sn from " +
				"AclEntity a,UserRoleEntity ur where ur.role.id=a.pid and ur.user.id=? and a.stype=? and a.rtype=?";
		List<Integer> rids = super.findList(hql, userId,RoleEntity.PRINCIPAL_TYPE,MenuResEntity.RES_TYPE);
		BasicSysKitUtil.mergeList(uids, rids);
		return uids;
	}

	private List<String> listMenuSnByPrin(int sid,String stype) {
		String hql = "select a.aclState from AclEntity a,MenuResEntity mr where a.rid=mr.id and a.sid=? and a.stype=? and a.rtype=?";
		List<String> sns = super.findList(hql,sid,stype,MenuResEntity.RES_TYPE);
		return sns;
	}

	private List<Integer> listMenuIdByPrin(int sid,String stype) {
		String hql = "select a.sid from AclEntity a where a.aclState=1 and a.sid=? and a.stype=? and a.rtype=?";
		List<Integer> ids = super.findList(hql,sid,stype,MenuResEntity.RES_TYPE);
		return ids;
	}
	
}
