package com.zhongtian.datascene.auth.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zhongtian.datascene.basic.excepiton.SysException;

/**
 * 权限控制的关键类，这个类用来存储主体和资源之间的关系，用来确定主体能够对哪些资源进行哪些操作
 * @author 
 *
 */
@Entity
@Table(name="t_acl")
public class AclEntity {
	private int id;
	private int sid;
	private String stype;
	private int rid;
	private String rtype;
	private int aclState;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 主体类型
	 * @return
	 */
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	/**
	 * 资源的类型
	 * @return
	 */
	public String getRtype() {
		return rtype;
	}
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	/**
	 * 主体的id
	 * @return
	 */
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	/**
	 * 资源的id
	 * @return
	 */
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	@Column(name="acl_state")
	public int getAclState() {
		return aclState;
	}
	public void setAclState(int aclState) {
		this.aclState = aclState;
	}
}
