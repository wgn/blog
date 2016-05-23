package com.zhuani21.blog.sqlite.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.bean.CookieUser;
import com.zhuani21.blog.sqlite.dao.DbActionDao;
import com.zhuani21.blog.sqlite.service.DbActionService;
import com.zhuani21.blog.sqlite.vo.DbActionVo;

@Service
public class DbActionServiceImpl implements DbActionService {
	@Autowired
	DbActionDao dbActionDao ;
	
	public List<String> selectTables(){
		return dbActionDao.selectTables();
	}
	public void createTable(DbActionVo dbActionVo){
		dbActionDao.createTable(dbActionVo);
	}
	@Override
	public List<CookieUser> selectCookieUserMapperData() {
		return dbActionDao.selectCookieUserMapperData();
	}
}
