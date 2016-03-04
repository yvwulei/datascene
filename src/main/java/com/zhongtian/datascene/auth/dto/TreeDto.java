package com.zhongtian.datascene.auth.dto;

public class TreeDto {
	private int id;
	private String name;
	private Integer pid;
	
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public TreeDto() {
	}
	public TreeDto(int id, String name, int pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "TreeDto [id=" + id + ", name=" + name + ", pid=" + pid + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		TreeDto td = (TreeDto)obj;
		return td.getId()==this.id;
	}
}
