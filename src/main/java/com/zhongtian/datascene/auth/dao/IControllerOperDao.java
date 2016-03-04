package com.zhongtian.datascene.auth.dao;

import java.util.List;

import com.zhongtian.datascene.auth.vo.ControllerOperEntity;
import com.zhongtian.datascene.basic.dao.IBaseDao;


public interface IControllerOperDao extends IBaseDao<ControllerOperEntity>{
	/**
	 * 通过rsn , sn 获取实体
	 * @param rsn
	 * @param sn
	 * @return
	 */
	public ControllerOperEntity loadOperBySn(String rsn, String sn);
	
	public List<ControllerOperEntity> listResByParent(Integer rid);
	
	public List<ControllerOperEntity> listOperByRes(Integer rid);
	
	public ControllerOperEntity loadOperById(int operId);
}
