package com.zhuani21.blog.service;

import com.zhuani21.blog.auto.bean.User;

public interface LoginAuthService {
	public User findUserByUsernamePassword(String username,String password) throws Exception;
}
