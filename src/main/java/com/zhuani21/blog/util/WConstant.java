package com.zhuani21.blog.util;
/**
 * 常量类
 * @author 吹棉
 */
public class WConstant {
	/**用户登陆状态session字符串，从session中取到的是user对象*/
	public static final String SESSION_LOGIN_USER;
	
	//以后考虑读取配置文件
	static{
		SESSION_LOGIN_USER = "session_login_user";
	}
	

}
