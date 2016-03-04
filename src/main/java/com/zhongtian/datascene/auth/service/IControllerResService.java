package com.zhongtian.datascene.auth.service;

import java.util.List;

import com.zhongtian.datascene.auth.dto.TreeDto;
import com.zhongtian.datascene.auth.vo.ControllerOperEntity;
import com.zhongtian.datascene.auth.vo.ControllerResEntity;

public interface IControllerResService {
	
	public void add(ControllerResEntity cr);
	
	public void addResources(ControllerResEntity cr,String psn);
	
	public void addOper(ControllerOperEntity oper,String rsn);
	
	public void updateOper(ControllerOperEntity oper);
	
	public void deleteOper(int operId);
	
	public List<ControllerResEntity> listResByParent(Integer pid);
	/**
	 * 根据资源id列表资源的操作对象
	 * @param rid
	 * @return
	 */
	public List<ControllerOperEntity> listOperByRes(Integer rid);
	
	public ControllerOperEntity loadOperById(int operId);
	/**
	 * 通过资源的sn和操作的sn获取操作对象
	 * @param rsn
	 * @param sn
	 * @return
	 */
	public ControllerOperEntity loadOperBySn(String rsn,String sn);
	
	public List<TreeDto> tree();
	/**
	 * 根据报名初始化ControllerResourcesEntity资源
	 * @param packages
	 */
	public void addInitControllerRes(String[] packages);
	
}
