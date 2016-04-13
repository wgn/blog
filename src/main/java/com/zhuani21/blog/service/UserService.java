package com.zhuani21.blog.service;

import java.util.List;

import com.zhuani21.blog.auto.bean.User;

public interface UserService {
	
	public List<User> selectUserList();

	public User selectUserById(Integer userId);

	public void updateUser(User user);

}
