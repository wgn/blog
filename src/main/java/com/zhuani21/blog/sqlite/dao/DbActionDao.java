package com.zhuani21.blog.sqlite.dao;

import java.util.List;

import com.zhuani21.blog.bean.CookieUser;
import com.zhuani21.blog.sqlite.vo.DbActionVo;

public interface DbActionDao {
	public List<String> selectTables();
	public void createTable(DbActionVo dbActionVo);
	public List<CookieUser> selectCookieUserMapperData();
}
