package com.zhuani21.blog.sqlite.dao;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.bean.CookieUser;

public interface CookieDao {
	public User selectUserByCookieKey(String key);
	public void insertCookieUser(CookieUser cookieUser);
	public void deleteCookieUserByUserId(Integer userId);
}
