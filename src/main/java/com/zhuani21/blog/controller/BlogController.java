package com.zhuani21.blog.controller;

<<<<<<< HEAD
import java.util.Calendar;

=======
>>>>>>> f3885674040c9c9bbe2419a1017c37fc9cbbee33
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
<<<<<<< HEAD
import org.apache.log4j.Logger;
=======
>>>>>>> f3885674040c9c9bbe2419a1017c37fc9cbbee33
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import com.zhuani21.blog.bean.Blog;
=======
>>>>>>> f3885674040c9c9bbe2419a1017c37fc9cbbee33
import com.zhuani21.blog.data.UserBlogDataMapper;
import com.zhuani21.blog.util.WDate;

@Controller
@RequestMapping("/blog")
public class BlogController {
<<<<<<< HEAD
	
	private static Logger logger = Logger.getLogger(BlogController.class); 
=======
>>>>>>> f3885674040c9c9bbe2419a1017c37fc9cbbee33

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
