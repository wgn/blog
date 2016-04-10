package com.zhuani21.blog.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.data.UserBlogDataMapper;
import com.zhuani21.blog.debug.BlogDebugBean;
import com.zhuani21.blog.util.SerializeObject;
import com.zhuani21.blog.util.WDate;

@Controller
@RequestMapping("/debug")
public class DebugController {

	private static Logger logger = Logger.getLogger(DebugController.class); 

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
	@RequestMapping("/blog")
	public ModelAndView index(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		//监测blog序列化的目录中文件是不是存在
		BlogDebugBean blogDebug = getBlogDebugInfo();	
		modelAndView.addObject("blogDebug", blogDebug);
		modelAndView.setViewName("blogDebug");
		return modelAndView;
	}
	private BlogDebugBean getBlogDebugInfo() {
		BlogDebugBean blogDebug = null;
		String blogDirPath = SerializeObject.getBlogFilePath();
		blogDebug = new BlogDebugBean();
		blogDebug.setBlogDirPath(blogDirPath);
		File blogDir = new File(blogDirPath);
		if(blogDir.exists() && blogDir.isDirectory()){
			blogDebug.setBlogDirExist(true);
			String[] blogs = blogDir.list();
			if(null!=blogs && blogs.length>0){
				List<String> blogFileNameList = new ArrayList<String>();
				blogFileNameList = Arrays.asList(blogs);
				blogDebug.setBlogFileNameList(blogFileNameList);
			}
		}
		return blogDebug;
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