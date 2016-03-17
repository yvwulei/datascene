package com.zhongtian.datascene.auth.dao;

import java.util.List;
import java.util.Map;

import com.zhongtian.datascene.auth.vo.AclEntity;
import com.zhongtian.datascene.basic.dao.IBaseDao;

public interface IAclDao extends IBaseDao<AclEntity> {
	/**
	 * 根据主体id和主体类型，资源id和资源类型获取ACL对象
	 * @param pid
	 * @param ptype
	 * @param rid
	 * @param rtype
	 * @return
	 */
	public AclEntity loadAcl(int pid,String ptype,int rid,String rtype);
	
	/**
	 * 根据角色的id获取该角色可以访问的所有的菜单的sn
	 * @param roleId
	 * @return
	 */
	public List<String> listMenuSnByRole(Integer roleId);
	/**
	 * 根据用户id获取该用户可以访问的所有菜单的sn
	 * 流程也是先获取该用户角色的数据之后获取用户自己所授权的数据
	 * @param userId
	 * @return
	 */
	public List<String> listMenuSnByUser(Integer userId);
	/**
	 * 根据角色的id获取该角色所以的菜单的id
	 * @param roleId
	 * @return
	 */
	public List<Integer> listMenuIdByRole(Integer roleId);
	
	/**
	 * 根据的id获取该角色所以的菜单的id
	 * @param roleId
	 * @return
	 */
	public List<Integer> listMenuIdByUser(Integer userId);
	
	/**
	 * 根据角色的id获取该角色所以的菜单的href
	 * @param roleId
	 * @return
	 */
	public List<Integer> listMenuHrefByRole(Integer roleId);
	
	/**
	 * 根据的id获取该角色所以的菜单的href
	 * @param roleId
	 * @return
	 */
	public List<Integer> listMenuHrefByUser(Integer userId);
	
}
