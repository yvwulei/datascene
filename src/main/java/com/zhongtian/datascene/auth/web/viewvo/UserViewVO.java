package com.zhongtian.datascene.auth.web.viewvo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserViewVO {
	/**
	 * 用户的名称
	 */
	@NotEmpty(message="{username.not.empty}")
	private String username;
	/**
	 * 用户的昵称
	 */
	@NotEmpty(message="{nickname.not.empty}")
	private String nickname;
	/**
	 * 用户的密码
	 */
	@Length(min=8, message="{password.length.error}") 
	private String password;
	/**
	 * 密码输入确认
	 */
	@NotEmpty(message="{confirmpassword.not.empty}")
	private String confirmPassword;
	/**
	 * 用户的邮箱
	 */
	@NotEmpty(message="{email.not.empty}")
	@Email(message="{email.not.correct}")
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public int getCertType() {
		return certType;
	}

	public void setCertType(int certType) {
		this.certType = certType;
	}

	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

}
