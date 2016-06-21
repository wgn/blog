package com.zhuani21.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.bean.CookieUser;
import com.zhuani21.blog.sqlite.service.DbActionService;
import com.zhuani21.blog.sqlite.vo.DbActionVo;

@Controller
@RequestMapping("/dbaction")
public class DbActionController {
	private static Logger logger = Logger.getLogger(DbActionController.class);

	@Autowired
	DbActionService dbActionService;

	@RequestMapping("/tableList")
	public ModelAndView tableList(){
		ModelAndView modelAndView = new ModelAndView();
		List<String> tableList = dbActionService.selectTables();
		modelAndView.addObject("tableList", tableList);
		modelAndView.setViewName("dbActionTableList");
		return modelAndView;
	}
	
	@RequestMapping("/createTableView")
	public ModelAndView createTableView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dbActionCreateTableView");
		return modelAndView;
	}

	@RequestMapping("/createTable")
	public @ResponseBody DbActionVo createTable(@RequestBody DbActionVo dbActionVo) {
		try {
			dbActionService.createTable(dbActionVo);
			dbActionVo.setCreateTableResult("Succ,建表成功！");
		} catch (Exception e) {
			dbActionVo.setCreateTableResult("Fail,建表失败." + e.getMessage());
		}
		return dbActionVo;
	}
	@RequestMapping("/loadData/{tableName}")
	public ModelAndView loadData(@PathVariable("tableName") String tableName){
		ModelAndView modelAndView = new ModelAndView();
		if("CookieUserMapper".equals(tableName)){
			List<CookieUser> cookieUserList =  dbActionService.selectCookieUserMapperData();
			List<String> cookieUserStrList = new ArrayList<String>();
			for(CookieUser cu : cookieUserList){
				cookieUserStrList.add(cu.toString());
			}
			modelAndView.addObject("dataList", cookieUserStrList);
		}
		
		modelAndView.addObject("tableName", tableName);
		modelAndView.setViewName("dbActionTableData");
		return modelAndView;
	}
	@RequestMapping("/cleanData/{tableName}")
	public ModelAndView cleanData(@PathVariable("tableName") String tableName){
		ModelAndView modelAndView = new ModelAndView();
		
		if(StringUtils.isNotBlank(tableName)){
			Integer n = dbActionService.deleteTableData(tableName);
			logger.info("删除了 " + tableName + "表的所有数据，共" + n + "条");
		}
		
		modelAndView.addObject("tableName", tableName);
		modelAndView.setViewName("dbActionTableData");
		return modelAndView;
	}
}














