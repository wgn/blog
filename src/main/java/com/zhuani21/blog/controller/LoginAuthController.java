package com.zhuani21.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.LoginAuth;
import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.service.LoginAuthService;

@Controller
public class LoginAuthController {
	@Autowired
	LoginAuthService loginAuthService;
	
	
	@RequestMapping(value={"/index"})
	public ModelAndView index(HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView() ;
		User user = (User) session.getAttribute("user");
		if(null!=user){
			modelAndView.setViewName("index");
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value={"/login"})
	public ModelAndView login(HttpSession session,LoginAuth auth) throws Exception {
		String username = auth.getUsername();
		String password = auth.getPassword();
		ModelAndView modelAndView = loginAndSaveSession(session, username, password);
		return modelAndView;
	}

	private ModelAndView loginAndSaveSession(HttpSession session, String username, String password) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		User u  = (User) session.getAttribute("user");
		if(null!=u){
			modelAndView.setViewName("redirect:/blog/admin");
			return modelAndView;
		}
		
		if(StringUtils.isBlank(username) && StringUtils.isBlank(password)){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		User user = loginAuthService.findUserByUsernamePassword(username, password);
		if(null!=user){
			session.setAttribute("user", user);
			modelAndView.addObject("user",user);
			modelAndView.setViewName("redirect:/blog/admin");
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
