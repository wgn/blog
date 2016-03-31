package com.zhuani21.blog.controller;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.data.UserBlogDataMapper;
import com.zhuani21.blog.util.WDate;

@Controller
@RequestMapping("/blog")
public class BlogController {

	private static Logger logger = Logger.getLogger(BlogController.class); 

	@RequestMapping("/admin")
	public ModelAndView admin(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		/*String license = req.getParameter("license");
		if(StringUtils.isNoneBlank(license)){*/
			modelAndView.addObject("admin",true);
		/*}*/
		
		modelAndView.addObject("blogList", UserBlogDataMapper.getBlog("admin").getList());
			
		modelAndView.setViewName("blogIndex");
		return modelAndView;
	}
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("blogList",  UserBlogDataMapper.getBlog("admin").getList());
		modelAndView.setViewName("blogIndex");
		return modelAndView;
	}
	@RequestMapping("/add")
	public String add(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		String content = req.getParameter("content");
		if(StringUtils.isNoneBlank(content)){
			String today = new WDate("yyyy/MM/dd").toString();
			String preStr = "--------------------- " + today + " \r\n\r\n";
			String res = preStr + content+"\r\n\r\n";
			UserBlogDataMapper.getBlog("admin").add(res);
		}
		return "redirect:/blog/admin.action";
	}
}
