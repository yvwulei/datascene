package com.zhongtian.datascene.auth.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public AclEntity loadAcl(int pid, String ptype, int rid, String rtype) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pid", pid);
		params.put("ptype", ptype);
		params.put("rid", rid);
		params.put("rtype", rtype);
		return (AclEntity)super.findByProperty(params).get(0);
	}
	
	private List<Integer> listOperIdsByResPrin(int rid,String rtype,int pid,String ptype) {
		String hql = "select a.aclState,co.id,co.indexPos from Acl a,ControllerOper co where " +
				"a.rid=co.rid and a.rid=? and a.rtype=? and a.pid=? and a.ptype=?";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pid", pid);
		params.put("ptype", ptype);
		params.put("rid", rid);
		params.put("rtype", rtype);
		List<Object[]> objs = super.findList(hql, params);
		return getOperIds(objs);
	}
	
	private List<Integer> getOperIds(List<Object[]> objs) {
		List<Integer> ids = new ArrayList<Integer>();
		int aclState=0; int coid=0; int indexPos=0;
		for(Object[] aobj:objs) {
			aclState = (Integer)aobj[0];
			coid = (Integer)aobj[1];
			indexPos = (Integer)aobj[2];
			if(AclEntity.checkPermission(indexPos, aclState)) {
				ids.add(coid);
			}
		}
		return ids;
	}

	@Override
	public List<Integer> listRoleOperIdsByRes(Integer rid, String rtype,
			Integer roleId) {
		return listOperIdsByResPrin(rid,rtype,roleId,RoleEntity.PRINCIPAL_TYPE);
	}

	@Override
	public List<Integer> listUserOperIdsByRes(Integer rid, String rtype,
			Integer userId) {
		List<Integer> ids = new ArrayList<Integer>();
		List<Integer> uids = listUserSelfOperIdsByRes(rid, rtype, userId);
		ids.addAll(uids);
		String hql = "select a.aclState,co.id,co.indexPos from Acl a,ControllerOper co,UserRole ur where " +
				"a.rid=co.rid and ur.role.id=a.pid and ur.user.id=? and a.rid=? and a.rtype=? and a.ptype=?";
		List<Object[]> objs = super.findList(hql, userId,rid,rtype,RoleEntity.PRINCIPAL_TYPE);
		List<Integer> rids = getOperIds(objs);
		BasicSysKitUtil.mergeList(ids, rids);
		return ids;
	}

	@Override
	public List<Integer> listUserSelfOperIdsByRes(Integer rid, String rtype,
			Integer userId) {
		return listOperIdsByResPrin(rid,rtype,userId,UserEntity.PRINCIPAL_TYPE);
	}
	
	private Map<String,List<String>> listAllResAndOperByPrin(Integer pid,String ptype,String rtype) {
		String hql = "select a.aclState,co.indexPos,cr.className,co.methodName from Acl a,ControllerResources cr,ControllerOper co where" +
				"a.rid=cr.id and cr.id=co.rid and a.pid=? and a.ptype=? and a.rtype=?";
		List<Object[]> objs = super.findList(hql,pid,ptype,rtype);
		return getResAndOper(objs);
	}

	private Map<String, List<String>> getResAndOper(List<Object[]> objs) {
		int aclState=0; int indexPos=0; String className=null; String methodName=null;
		Map<String,List<String>> maps = new HashMap<String, List<String>>();
		for(Object[] aobj:objs) {
			aclState = (Integer)aobj[0];
			indexPos = (Integer)aobj[1];
			className = (String)aobj[2];
			methodName = (String)aobj[3];
			if(AclEntity.checkPermission(indexPos, aclState)) {
				if(!maps.containsKey(className)) {
					maps.put(className, new ArrayList<String>());
					addMethodToList(maps,className,methodName);
				} else {
					addMethodToList(maps,className,methodName);
				}
			}
		}
		return maps;
	}

	private void addMethodToList(Map<String, List<String>> maps,
			String className, String methodName) {
		List<String> ms = maps.get(className);
		String[] mns = methodName.split("\\|");
		for(String m:mns) {
			if(!ms.contains(m)) ms.add(m);
		}
	}

	@Override
	public Map<String, List<String>> listAllResAndOperByRole(Integer roleId,String rtype) {
		return listAllResAndOperByPrin(roleId,RoleEntity.PRINCIPAL_TYPE,rtype);
	}

	@Override
	public Map<String, List<String>> listAllResAndOperByUser(Integer userId,String rtype) {
		Map<String,List<String>> umaps = listAllResAndOperByPrin(userId,UserEntity.PRINCIPAL_TYPE,rtype);
		String hql = "select a.aclState,co.indexPos,cr.className,co.methodName from Acl a,ControllerResources cr,ControllerOper co,UserRole ur where" +
				"a.rid=cr.id and cr.id=co.rid and ur.role.id=a.pid and ur.user.id=? and a.ptype=? and a.rtype=?";
		List<Object[]> objs = super.findList(hql, userId,RoleEntity.PRINCIPAL_TYPE,rtype);
		Map<String,List<String>> rmaps = getResAndOper(objs);
		Set<String> rkeys = rmaps.keySet();
		for(String rk:rkeys) {
			if(umaps.containsKey(rk)) {
				BasicSysKitUtil.mergeList(umaps.get(rk), rmaps.get(rk));
			} else {
				umaps.put(rk, rmaps.get(rkeys));
			}
		}
		return umaps;
	}
	
	private List<String> listMenuSnByPrin(int pid,String ptype) {
		String hql = "select a.aclState,mr.sn from Acl a,MenuResources mr where a.rid=mr.id and a.pid=? and a.ptype=? and a.rtype=?";
		List<Object[]> objs = super.findList(hql,pid,ptype,MenuResEntity.RES_TYPE);
		return getMenuSn(objs);
	}

	private List<String> getMenuSn(List<Object[]> objs) {
		int aclState=0; String sn = null;
		List<String> sns = new ArrayList<String>();
		for(Object[] aobj:objs) {
			aclState = (Integer)aobj[0];
			sn = (String)aobj[1];
			if(AclEntity.checkPermission(0, aclState)) {
				sns.add(sn);
			}
		}
		return sns;
	}

	@Override
	public List<String> listMenuSnByRole(Integer roleId) {
		return listMenuSnByPrin(roleId,RoleEntity.PRINCIPAL_TYPE);
	}

	@Override
	public List<String> listMenuSnByUser(Integer userId) {
		List<String> usns = listMenuSnByPrin(userId,UserEntity.PRINCIPAL_TYPE);
		String hql = "select a.aclState,mr.sn from " +
				"Acl a,MenuResources mr,UserRole ur where a.rid=mr.id and ur.role.id=a.pid and ur.user.id=? and a.ptype=? and a.rtype=?";
		List<Object[]> objs = super.findList(hql, userId,RoleEntity.PRINCIPAL_TYPE,MenuResEntity.RES_TYPE);
		List<String> rsns = getMenuSn(objs);
		BasicSysKitUtil.mergeList(usns, rsns);
		return usns;
	}
	
	private List<Integer> listMenuIdByPrin(int pid,String ptype) {
		String hql = "select a.aclState,a.pid from Acl a where a.pid=? and a.ptype=? and a.rtype=?";
		List<Object[]> objs = super.findList(hql,pid,ptype,MenuResEntity.RES_TYPE);
		return getMenuIds(objs);
	}

	private List<Integer> getMenuIds(List<Object[]> objs) {
		int aclState=0; int id = 0;
		List<Integer> ids = new ArrayList<Integer>();
		for(Object[] aobj:objs) {
			aclState = (Integer)aobj[0];
			id = (Integer)aobj[1];
			if(AclEntity.checkPermission(0, aclState)) {
				ids.add(id);
			}
		}
		return ids;
	}

	@Override
	public List<Integer> listMenuIdByRole(Integer roleId) {
		return listMenuIdByPrin(roleId,RoleEntity.PRINCIPAL_TYPE);
	}

	@Override
	public List<Integer> listMenuIdByUser(Integer userId) {
		List<Integer> uids = listMenuIdByPrin(userId,UserEntity.PRINCIPAL_TYPE);
		String hql = "select a.aclState,mr.sn from " +
				"Acl a,UserRole ur where ur.role.id=a.pid and ur.user.id=? and a.ptype=? and a.rtype=?";
		List<Object[]> objs = super.findList(hql, userId,RoleEntity.PRINCIPAL_TYPE,MenuResEntity.RES_TYPE);
		List<Integer> rids = getMenuIds(objs);
		BasicSysKitUtil.mergeList(uids, rids);
		return uids;
	}

	@Override
	public List<Integer> listMenuIdByUserSelf(Integer userId) {
		return listMenuIdByPrin(userId,UserEntity.PRINCIPAL_TYPE);
	}


}
