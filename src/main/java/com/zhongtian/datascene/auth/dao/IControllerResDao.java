package com.zhongtian.datascene.auth.dao;

import java.util.List;

import com.zhongtian.datascene.auth.dto.TreeDto;
import com.zhongtian.datascene.auth.vo.ControllerResEntity;
import com.zhongtian.datascene.basic.dao.IBaseDao;


public interface IControllerResDao extends IBaseDao<ControllerResEntity>{
	
	/**
	 * 添加Controller资源
	 * @param cr
	 * @param psn
	 */
	public void addResources(ControllerResEntity cr,String psn);
	
	/**
	 * 通过Pid获取所有的Controller资源
	 * @param pid
	 * @return
	 */
	public List<ControllerResEntity> listResByParent(Integer pid);
	
	public List<TreeDto> tree();
	
	public ControllerResEntity loadBySn(String sn);
	
}
