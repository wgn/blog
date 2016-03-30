package com.zhuani21.blog.dao;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.vo.LoginAuthCustomVo;

public interface LoginAuthDao {
	
	public User findUserByLoginAuth(LoginAuthCustomVo loginAuthCustomVo) throws Exception;
	
}
