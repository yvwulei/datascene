package com.zhongtian.datascene.auth.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_user")
public class UserEntity {
	public static final String PRINCIPAL_TYPE="user";
	/**
	 * 用户的标识
	 */
	private int id;
	/**
	 * 用户的名称
	 */
	private String username;
	/**
	 * 用户的昵称
	 */
	private String nickname;
	/**
	 * 用户的密码
	 */
	private String password;
	/**
	 * 用户的注册时间
	 */
	private String registerTime;
	/**
	 * 用户的邮箱
	 */
	private String email;
	/**
	 * 用户的电话
	 */
	private String phone;
	/**
	 * 用户的性别
	 */
	private int sex;
	/**
	 * 用户的证件类型
	 */
	private int certType;
	/**
	 * 用户的证件号码
	 */
	private String certNum;
	/**
	 * 用户的状态
	 */
	private int status;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@Column(name="cert_type")
	public int getCertType() {
		return certType;
	}
	public void setCertType(int certType) {
		this.certType = certType;
	}
	@Column(name="cert_num")
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
