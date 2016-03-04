package com.zhongtian.datascene.auth.dto;

import java.util.List;

import com.zhongtian.datascene.auth.vo.MenuResEntity;

public class LeftMenuDto implements Comparable<LeftMenuDto>{
	private MenuResEntity parent;
	private List<MenuResEntity> childs;
	
	public MenuResEntity getParent() {
		return parent;
	}
	public void setParent(MenuResEntity parent) {
		this.parent = parent;
	}
	public List<MenuResEntity> getChilds() {
		return childs;
	}
	public void setChilds(List<MenuResEntity> childs) {
		this.childs = childs;
	}
	
	@Override
	public boolean equals(Object obj) {
		LeftMenuDto lmd = (LeftMenuDto)obj;
		return lmd.getParent().getId()==this.getParent().getId();
	}
	
	@Override
	public int compareTo(LeftMenuDto o) {
		if(this.parent.getOrderNum()>o.getParent().getOrderNum()) return 1; 
		else if(this.parent.getOrderNum()<o.getParent().getOrderNum()) return -1;
		else return 0;
	}
}
