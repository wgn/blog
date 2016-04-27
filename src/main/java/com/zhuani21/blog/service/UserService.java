package com.zhuani21.blog.service;

import java.util.List;

import com.zhuani21.blog.auto.bean.LoginAuth;
import com.zhuani21.blog.auto.bean.User;

public interface UserService {
	
	public List<User> selectUserList();

	public User selectUserById(Integer userId);

	public void updateUser(User user);

	public void insertUser(User user);

	public LoginAuth selectLoginAuthByUserId(Integer userId);

	public void updateLoginAuth(LoginAuth loginAuth);

	public void insertLoinAuth(LoginAuth loginAuth);
	
	public void updateLoginAuthSelective(LoginAuth loginAuth);

}
