package com.zhuani21.blog.sqlite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zhuani21.blog.bean.CookieUser;
import com.zhuani21.blog.sqlite.vo.DbActionVo;

@Service
public interface DbActionService {
	
	
	public List<String> selectTables();
	public void createTable(DbActionVo dbActionVo);
	public List<CookieUser> selectCookieUserMapperData();
	public Integer deleteTableData(String tableName);
}
