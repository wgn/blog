package com.zhuani21.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.bean.UserVo;
import com.zhuani21.blog.service.UserService;
import com.zhuani21.blog.util.BeanCopyUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<User> userList = userService.selectUserList();
		List<UserVo> userVoList = BeanCopyUtils.getCustomBeanList(userList, UserVo.class);
		if(null!=userList && userList.size()>0){
			modelAndView.addObject("userList", userVoList);
		}
		modelAndView.setViewName("userList");
		return modelAndView;
		
	}
}
