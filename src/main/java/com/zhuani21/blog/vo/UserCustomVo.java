package com.zhuani21.blog.vo;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.bean.UserCustom;

public class UserCustomVo {
	User user = null;
	UserCustom userCustom = null;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserCustom getUserCustom() {
		return userCustom;
	}
	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
}
