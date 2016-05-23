package com.zhuani21.blog.bean;

import java.util.Date;

public class CookieUser {
	private Long mapper_id;
	
	private String mapper_key;
	
	private Integer id;

	private String nickname;

	private String sex;

	private Date birthday;

	private String address;

	private String email;

	private String mobile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public Long getMapper_id() {
		return mapper_id;
	}

	public void setMapper_id(Long mapper_id) {
		this.mapper_id = mapper_id;
	}

	public String getMapper_key() {
		return mapper_key;
	}

	public void setMapper_key(String mapper_key) {
		this.mapper_key = mapper_key;
	}

	@Override
	public String toString() {
		return "CookieUser [mapper_id=" + mapper_id + ", mapper_key=" + mapper_key + ", id=" + id + ", nickname=" + nickname + ", sex=" + sex + ", birthday=" + birthday
				+ ", address=" + address + ", email=" + email + ", mobile=" + mobile + "]";
	}

}
