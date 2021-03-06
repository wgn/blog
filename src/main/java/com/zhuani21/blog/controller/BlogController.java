package com.zhuani21.blog.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.Blog;
import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.bean.BlogEx;
import com.zhuani21.blog.service.BlogService;
import com.zhuani21.blog.util.WConstant;
import com.zhuani21.blog.util.WDate;

@Controller
@RequestMapping("/blog")
public class BlogController {

	//private static Logger logger = Logger.getLogger(BlogController.class); 
	@Autowired
	BlogService blogService;
	
	@RequestMapping(value={"/index"},method={RequestMethod.GET})
	public ModelAndView index(HttpSession session) throws Exception {
		
		ModelAndView modelAndView = getBlogList(getUserId(session), 0);
		//管理员标志
		modelAndView.addObject("admin",true);
		
		return modelAndView;
	}

	@RequestMapping(value={"/save"},method={RequestMethod.POST})
	public @ResponseBody Blog save(@RequestBody Blog blog,HttpSession session) throws Exception {
		if(null==blog){
			return null;
		}
		String content = blog.getContent();
		if(StringUtils.isNoneBlank(content)){
			String today = new WDate("yyyy/MM/dd").toString();
			String preStr = "--------------------- " + today + " \r\n\r\n";
			String res = preStr + content+"\r\n\r\n";
			blog.setContent(res);
			//其他的值都有默认值，只需要设置这个足够了
			blog.setUserId(getUserId(session));
			blogService.addBlog(blog);
			return transferBlog(blog);
		}
		return null;
	}
	
	private static Integer getUserId(HttpSession session){
		User user = (User) session.getAttribute(WConstant.SESSION_LOGIN_USER);
		return user.getId();
	}
	
	@RequestMapping(value={"/loadMore/{pageIndex}"},method={RequestMethod.GET})
	public @ResponseBody List<Blog> loadMore(@PathVariable("pageIndex") Integer pageIndex,HttpSession session) throws Exception {
		List<Blog> blogList = blogService.getBlogList(getUserId(session), pageIndex);
		return blogList;
	}

	private ModelAndView getBlogList(Integer userId,Integer page) {
		ModelAndView modelAndView = new ModelAndView();
		//admin的userId是1，先写1测试.页数是0，因为第一次登陆0-100条。
		List<Blog> blogList = blogService.getBlogList(userId, page);
		List<Blog> newBlogList = transferBlogList(blogList);
		//原来保存文件的方式。
		modelAndView.addObject("blogList", newBlogList);
		modelAndView.addObject("pageIndex", BlogEx.ONE_PAGE_COUNT);
		modelAndView.setViewName("blogIndex");
		return modelAndView;
	}
	/**
	 * 因为某些人总是上传一些标签代码什么的，讨厌死了，没办法我要对文本内容进行一下过滤。
	 * @param blogList
	 * @return
	 */
	private List<Blog> transferBlogList(List<Blog> blogList) {
		List<Blog> resList = new ArrayList<Blog>();
		if(null!=blogList){
			for(Blog b:blogList){
				resList.add(transferBlog(b));
			}
		}
		return resList;
	}
	private Blog transferBlog(Blog blog){
		String key = "&lt;";
		String newContent = blog.getContent().replaceAll("<", key);
		newContent = newContent.replaceAll(key+"img", "<img").replaceAll(key+"/img", "</img");
		newContent = newContent.replaceAll(key+"a", "<a").replaceAll(key+"/a", "</a");
		blog.setContent(newContent);
		return blog;
	}
}
