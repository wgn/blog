package com.zhuani21.blog.sqlite.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.bean.CookieUser;
import com.zhuani21.blog.sqlite.dao.CookieDao;
import com.zhuani21.blog.sqlite.service.CookieService;
import com.zhuani21.blog.util.WID;
@Service("cookieService")
public class CookieServiceImpl implements CookieService {
	@Autowired
	CookieDao cookieDao;

	@Override
	public void put(String k, User user) {
		insertCookieUser(k, user);
	}
	//这样以insert开头，才受到事务管理
	private void insertCookieUser(String k, User user) {
		Integer userId = user.getId();
		if (userId!=null) {
			cookieDao.deleteCookieUserByUserId(userId);
		}
		CookieUser cookieUser = new CookieUser();
		BeanUtils.copyProperties(user, cookieUser);
		cookieUser.setMapper_id(WID.getLong());
		cookieUser.setMapper_key(k);
		cookieDao.insertCookieUser(cookieUser);
	}

	@Override
	public User get(String k) {
		return cookieDao.selectUserByCookieKey(k);
	}

}
