package com.zhongtian.datascene.auth.dao;

import java.util.List;

import com.zhongtian.datascene.auth.dto.LeftMenuDto;
import com.zhongtian.datascene.auth.dto.TreeDto;
import com.zhongtian.datascene.auth.vo.MenuResEntity;
import com.zhongtian.datascene.basic.dao.IBaseDao;

/**
 * 菜单资源对象的接口
 * @author Konghao
 *
 */
public interface IMenuResDao extends IBaseDao<MenuResEntity> {

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
	
	public MenuResEntity loadBySn(String sn);
	
	/**
	 * 根据角色ID获取所有的资源
	 * 
	 */
	public List<MenuResEntity> listByRoleId(Integer rid);
	
	/**
	 * 根据用户ID获取所有的资源
	 * 
	 */
	public List<MenuResEntity> listByUserId(Integer uid);
	
}
