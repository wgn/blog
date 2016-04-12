package com.zhuani21.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.auto.mapper.UserMapper;
import com.zhuani21.blog.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<User> selectUserList() {
		return userMapper.selectByExample(null);
	}
}
