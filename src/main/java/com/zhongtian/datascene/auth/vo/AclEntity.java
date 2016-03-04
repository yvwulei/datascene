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
	private String stype;
	private String rtype;
	private int sid;
	private int rid;
	private int aclState;
	
	public void setMenuType() {
		this.rtype = MenuResEntity.RES_TYPE;
	}
	
	public void setControllerType() {
		this.rtype = ControllerResEntity.RES_TYPE;
	}
	
	public void setUserType() {
		this.stype = UserEntity.PRINCIPAL_TYPE;
	}
	
	public void setRoleType() {
		this.stype = RoleEntity.PRINCIPAL_TYPE;
	}
	
	/**
	 * 设置权限，在某个位置设置访问或者无法访问
	 * @param index
	 * @param permit
	 */
	public void setPermission(int index,boolean permit) {
		if(index<0||index>31) throw new SysException("权限的位置只能在0-31之间");
		this.aclState = setBit(this.aclState,index,permit);
	}
	/**
	 * 具体进行设置
	 * @param state
	 * @param index
	 * @param permit
	 */
	public int setBit(int state,int index,boolean permit) {
		int tmp = 1;
		tmp = tmp<<index;
		if(permit) {
			state = state|tmp;
		} else {
			tmp = ~tmp;
			state = state&tmp;
		}
		return state;
	}
	
	/**
	 * 检查在某个位置是否可以访问
	 * @param index
	 * @return
	 */
	public boolean checkPermission(int index) {
		int tmp = 1;
		tmp = tmp<<index;
		int num = this.aclState&tmp;
		return num>0;
	}
	
	public static boolean checkPermission(int index,int aclState) {
		int tmp = 1;
		tmp = tmp<<index;
		int num = aclState&tmp;
		return num>0;
	}
	
	
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
	/**
	 * 对方法的操作状态，存储的是一个4个字节的整数，其实就可以存储0-31位的操作
	 * 000000....1011-->可以进行CREATE,READ,DELETE,无法进行UPDATE
	 * 数据库中存储的值是11
	 * @return
	 */
	@Column(name="acl_state")
	public int getAclState() {
		return aclState;
	}
	public void setAclState(int aclState) {
		this.aclState = aclState;
	}
}
