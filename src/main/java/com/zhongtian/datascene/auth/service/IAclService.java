package com.zhongtian.datascene.auth.service;

import java.util.List;

import com.zhongtian.datascene.auth.vo.AclEntity;

public interface IAclService {
	
	/**
	 * 添加授权实体
	 * @param acl
	 */
	public void add(AclEntity acl);
	
	/**
	 * 根据主体id和主体类型，资源id和资源类型获取AclEntity对象
	 * @param pid
	 * @param ptype
	 * @param rid
	 * @param rtype
	 * @return
	 */
	public AclEntity loadAcl(int sid,String stype,int rid,String rtype);
	
	/**
	 * 根据角色id、资源类型和资源id获取AclEntity对象
	 * @param pid
	 * @param rid
	 * @param rtype
	 * @return
	 */
	public AclEntity loadAclByRole(int roleId,int rid,String rtype);
	
	/**
	 * 根据用户id、资源类型和资源id获取AclEntity对象
	 * @param pid
	 * @param rid
	 * @param rtype
	 * @return
	 */
	public AclEntity loadAclByUser(int userId,int rid,String rtype);
	
	
	/**
	 * 根据角色id、获取该用户可以访问的所有菜单的sn
	 * @param pid
	 * @param rid
	 * @param rtype
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
	 * 根据角色的id获取该角色所有的菜单的id
	 * @param roleId
	 * @return
	 */
	public List<Integer> listMenuIdByRole(Integer roleId);
	
	/**
	 * 根据用户的id获取该用户所有的菜单的id，先获取角色的数据，再获取用户独立的数据
	 * @param roleId
	 * @return
	 */
	public List<Integer> listMenuIdByUser(Integer userId);
	
	/**
	 * 根据角色的id获取该角色所有的菜单的id
	 * @param roleId
	 * @return
	 */
	public List<Integer> listMenuHrefByRole(Integer roleId);
	
	/**
	 * 根据用户的id获取该用户所有的菜单的id，先获取角色的数据，再获取用户独立的数据
	 * @param roleId
	 * @return
	 */
	public List<Integer> listMenuUrlByUser(Integer userId);
	
}
