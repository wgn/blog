package com.zhuani21.blog.controller;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.Blog;
import com.zhuani21.blog.bean.BlogJsonVO;
import com.zhuani21.blog.data.UserBlogDataMapper;
import com.zhuani21.blog.service.BlogService;
import com.zhuani21.blog.util.WDate;

@Controller
@RequestMapping("/blog")
public class BlogController {

	private static Logger logger = Logger.getLogger(BlogController.class); 
	@Autowired
	BlogService blogService;
	
	@RequestMapping("/admin")
	public ModelAndView admin(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		ModelAndView modelAndView = getBlogList(null, null);
		//管理员标志
		modelAndView.addObject("admin",true);
		return modelAndView;
	}
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		ModelAndView modelAndView = getBlogList(null, null);
		return modelAndView;
	}
	@RequestMapping("/add")
	public String add(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		Blog blog = new Blog();
		String content = req.getParameter("content");
		if(StringUtils.isNoneBlank(content)){
			String today = new WDate("yyyy/MM/dd").toString();
			String preStr = "--------------------- " + today + " \r\n\r\n";
			String res = preStr + content+"\r\n\r\n";
			blog.setContent(res);
		}
		//其他的值都有默认值，只需要设置这个足够了
		blog.setUserId(1);
		blogService.addBlog(blog);
		return "redirect:/blog/admin.action";
	}
	
	@RequestMapping("/loadMore")
	public @ResponseBody BlogJsonVO loadMore(@RequestBody BlogJsonVO json) throws Exception {
		logger.debug("page = " + json.getPage());
		
		List<Blog> blogList = blogService.getBlogList(1, json.getPage());
		if(null!=blogList && blogList.size()>0){
			StringBuffer res = new StringBuffer();
			for(Blog blog :  blogList){
				res.append("<pre>");
				res.append(blog.getContent());
				res.append("</pre>");
			}
			json.setRes(res.toString());
		}
		return json;
	}
	@RequestMapping("/loadMore1")
	public void loadMore1( Integer page,HttpServletRequest req,HttpServletResponse resp) throws Exception {
		logger.debug("page = " + page);
		String reqPage = req.getParameter("page");
		logger.debug("reqPage = " + reqPage);
		
		List<Blog> blogList = blogService.getBlogList(1, page);
		if(null!=blogList && blogList.size()>0){
			StringBuffer res = new StringBuffer();
			for(Blog blog :  blogList){
				res.append("<pre>");
				res.append(blog.getContent());
				res.append("</pre>");
			}
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.write(res.toString());
			out.close();
		}
	}
	
	
	private ModelAndView getBlogList(Integer userId,Integer page) {
		ModelAndView modelAndView = new ModelAndView();
		if(null==userId){
			userId = 1 ;
		}
		if(null== page){
			page = 0;
		}
		//admin的userId是1，先写1测试.页数是0，因为第一次登陆0-100条。
		List<Blog> blogList = blogService.getBlogList(userId, page);
		//原来保存文件的方式。
		
		modelAndView.addObject("blogList", blogList);

		modelAndView.setViewName("blogIndex");
		return modelAndView;
	}
}
