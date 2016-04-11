package com.zhuani21.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.service.LoginAuthService;

@Controller
public class LoginAuthController {
	@Autowired
	LoginAuthService loginAuthService;
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = req.getSession();
		User u  = (User) session.getAttribute("user");
		if(null!=u){
			modelAndView.setViewName("index");
			return modelAndView;
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(StringUtils.isBlank(username) && StringUtils.isBlank(password)){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		User user = loginAuthService.findUserByUsernamePassword(username, password);
		if(null!=user){
			req.getSession().setAttribute("user", user);
			modelAndView.addObject("user",user);
			modelAndView.setViewName("index");
		}else{
			modelAndView.addObject("username",username);
			modelAndView.addObject("loginErrorMsg", "用户名或密码错误");
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	@RequestMapping(value={"/logout"}, method={RequestMethod.GET})
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:login";
	}

}
