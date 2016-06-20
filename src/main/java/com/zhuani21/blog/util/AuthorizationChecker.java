package com.zhuani21.blog.util;

import com.zhuani21.blog.auto.bean.User;

public class AuthorizationChecker {
	
	public static final boolean isAdmin(User u){
		if(null!=u && 1==u.getId()){
			return true;
		}
		return false;
	}

}
