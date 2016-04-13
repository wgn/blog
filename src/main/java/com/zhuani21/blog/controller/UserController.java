package com.zhuani21.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.bean.UserCustom;
import com.zhuani21.blog.bean.UserVo;
import com.zhuani21.blog.service.UserService;
import com.zhuani21.blog.util.BeanCopyUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/view/list")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<User> userList = userService.selectUserList();
		if(null!=userList && userList.size()>0){
			modelAndView.addObject("userList", userList);
		}
		modelAndView.setViewName("userList");
		return modelAndView;
	}
	@RequestMapping(value={"/view/edit/{userId}"},method={RequestMethod.GET})
	public ModelAndView editView(@PathVariable Integer userId){
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.selectUserById(userId);
		modelAndView.addObject("user", user);
		modelAndView.addObject("opType", "edit");
		modelAndView.setViewName("userUpdate");
		return modelAndView;
	}
	
	@RequestMapping("/db/edit")
	public ModelAndView edit( User user){
		System.out.println(user);
		
		ModelAndView modelAndView = new ModelAndView();
		
		userService.updateUser(user);
		
		modelAndView.setViewName("redirect:/user/view/list");
		return modelAndView;
	}
	@RequestMapping(value={"/view/copy/{userId}"},method={RequestMethod.GET})
	public ModelAndView copyView(@PathVariable Integer userId){
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.selectUserById(userId);
		modelAndView.addObject("user", user);
		modelAndView.addObject("opType", "add");
		modelAndView.setViewName("userUpdate");
		return modelAndView;
	}
	@RequestMapping(value={"/view/add"},method={RequestMethod.GET})
	public ModelAndView addView(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("opType", "add");
		modelAndView.setViewName("userUpdate");
		return modelAndView;
	}
	@RequestMapping(value={"/db/add"}, method={RequestMethod.POST})
	public ModelAndView add(User user){
		ModelAndView modelAndView = new ModelAndView();
		user.setId(null);
		userService.updateUser(user);
		
		modelAndView.setViewName("userList");
		return modelAndView;
	}
}
