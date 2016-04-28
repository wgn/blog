package com.zhuani21.blog.bean;

public class UserVO {
	private Integer id;
	
	 private String nickname;

	    private String sex;

	    //private Date birthday;

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
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", nickname=" + nickname + ", sex=" + sex + ", address=" + address + ", email=" + email + ", mobile=" + mobile + "]";
	}

}
