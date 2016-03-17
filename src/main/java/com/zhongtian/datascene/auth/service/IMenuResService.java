package com.zhongtian.datascene.auth.service;

import java.util.List;

import com.zhongtian.datascene.auth.dto.LeftMenuDto;
import com.zhongtian.datascene.auth.dto.TreeDto;
import com.zhongtian.datascene.auth.vo.MenuResEntity;

public interface IMenuResService {
	
	public void add(MenuResEntity mr);
	
	public void add(MenuResEntity mr,String psn);
	/**
	 * 根据菜单的位置和父类Menu的sn获取所有的菜单资源对象
	 * @param psn
	 * @param pos
	 * @return
	 */
	public List<MenuResEntity> listModelMenuByType(String psn,int pos);
	/**
	 * 获取顶部的菜单资源对象
	 * @return
	 */
	public List<MenuResEntity> listTopMenu();
	/**
	 * 获取左边导航的菜单资源对象
	 * @return
	 */
	public List<LeftMenuDto> listLeftNav();
	
	public List<TreeDto> tree();
	
	public List<MenuResEntity> listByParent(Integer pid);
	
	public void addInitMenuResources(String[] packages);
	
	/**
	 * 根据角色RoleId,获取菜单资源
	 */
	public List<MenuResEntity> listByRoleId(Integer rid);
	
	/**
	 * 根据角色UserId,获取菜单资源
	 */
	public List<MenuResEntity> listByUserId(Integer uid);
	
}
