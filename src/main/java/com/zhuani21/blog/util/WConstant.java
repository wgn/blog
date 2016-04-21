package com.zhuani21.blog.util;

import java.util.UUID;

/**
 * 常量类
 * @author 吹棉
 */
public class WConstant {
	/**用户登陆状态session字符串，从session中取到的是user对象*/
	public static final String SESSION_LOGIN_USER;
	public static final String COOKIE_LOGIN_KEY;
	//以后考虑读取配置文件
	static{
		SESSION_LOGIN_USER = "session_login_user";
		COOKIE_LOGIN_KEY = "650AE772-2926-42FC-9D5D-E31B63D42D3B";//随机生成的。
	}
	
	public static void main(String[] args){
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString().toUpperCase());
	}
	

}
