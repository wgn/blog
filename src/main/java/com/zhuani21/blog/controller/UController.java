package com.zhuani21.blog.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.LoginAuth;
import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.service.UserService;
import com.zhuani21.blog.util.WConstant;

@Controller
@RequestMapping("/u")
public class UController {
	private static final Logger logger = Logger.getLogger(UController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value={"/info"})
	public ModelAndView uinfo(HttpSession session){
		User u = (User) session.getAttribute(WConstant.SESSION_LOGIN_USER);
		if(u!=null){
			Integer userId = u.getId();
			return uinfoModelAndView(userId);
		} else {
			return null;
		}
	}
	private ModelAndView uinfoModelAndView(Integer userId) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.selectUserById(userId);
		modelAndView.addObject("user", user);
		LoginAuth loginAuth = userService.selectLoginAuthByUserId(userId);
		modelAndView.addObject("userMimi", loginAuth);
		modelAndView.setViewName("userInfo");
		return modelAndView;
	}
	@RequestMapping(value={"/updateInfo"})
	public ModelAndView updateInfo(User user,HttpSession session){
		ModelAndView modelAndView = null;
		if(user!=null){
			User sessionUser = (User) session.getAttribute(WConstant.SESSION_LOGIN_USER);
			if(sessionUser.getId()!=user.getId()){
				logger.error("sessionUserID:"+sessionUser.getId()+"的用户想修改别人的信息,别人为：" + user.getId());
				throw new RuntimeException("您忒坏了吧");
			}else{
				userService.updateUser(user);
				modelAndView = uinfoModelAndView(user.getId());
				modelAndView.addObject("msg", "修改用户信息成功");
			}
		} else {
			logger.error("用户为空！");
			throw new RuntimeException("修改用户，用户信息有误");
		}
		return modelAndView;
	}
	@RequestMapping(value={"/updateMimiInfo"})
	public ModelAndView updateMimiInfo(LoginAuth loginAuth,HttpSession session){
		ModelAndView modelAndView = null;
		if(loginAuth!=null){
			User sessionUser = (User) session.getAttribute(WConstant.SESSION_LOGIN_USER);
			if(sessionUser.getId()!=loginAuth.getUserId()){
				logger.error("sessionUserID:"+sessionUser.getId()+"的用户想修改别人的信息,别人为：" + loginAuth.getUserId());
				throw new RuntimeException("您忒坏了吧");
			}else{
				userService.updateLoginAuthSelective(loginAuth);
				modelAndView = uinfoModelAndView(loginAuth.getUserId());
				modelAndView.addObject("msg", "修改登陆信息成功");
			}
		} else {
			logger.error("登陆信息为空！");
			throw new RuntimeException("修改登陆信息有误");
		}
		return modelAndView;
	}
}
