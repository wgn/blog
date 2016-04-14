package com.zhuani21.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.LoginAuth;
import com.zhuani21.blog.auto.bean.LoginAuthExample;
import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.auto.mapper.LoginAuthMapper;
import com.zhuani21.blog.auto.mapper.UserMapper;
import com.zhuani21.blog.exception.BlogBaseException;
import com.zhuani21.blog.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	LoginAuthMapper loginAuthMapper;
	
	@Override
	public List<User> selectUserList() {
		return userMapper.selectByExample(null);
	}

	@Override
	public User selectUserById(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertSelective(user);
	}

	@Override
	public LoginAuth selectLoginAuthByUserId(Integer userId) {
		LoginAuthExample example = new LoginAuthExample();
		if(null==userId || userId<=0){
			throw new BlogBaseException("根据用户ID：" + userId + "无效");
		}
		example.createCriteria().andUserIdEqualTo(userId);
		List<LoginAuth> loginAuthList = loginAuthMapper.selectByExample(example);
		if(null!=loginAuthList){
			if(loginAuthList.size()==1){
				return loginAuthList.get(0);
			}else{
				throw new BlogBaseException("根据用户ID：" + userId + "查询到了多个用户。请管理员检查错误");
			}
		}
		return null;
	}
}
