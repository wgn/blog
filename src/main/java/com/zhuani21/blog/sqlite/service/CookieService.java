package com.zhuani21.blog.sqlite.service;

import com.zhuani21.blog.auto.bean.User;

public interface CookieService {
	public  void put(String k,User user);
	public  User get(String k);
}
