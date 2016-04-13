package com.zhuani21.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.LoginAuth;
import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.service.UserService;
/**
 * 这个/user的URL地址被UserManageInterceptor拦截，只有ID为1的用户才能使用相关功能。
 * @author 吹棉
 */
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
		userService.insertUser(user);
		
		modelAndView.setViewName("redirect:/user/view/list");
		return modelAndView;
	}
	@RequestMapping(value={"/view/loginSetting/{userId}"})
	public ModelAndView loginSettingView(@PathVariable("userId") Integer userId){
		ModelAndView modelAndView = new ModelAndView();
		if(null==userId || userId<0){
			String errorMsg = "用户ID无效";
			modelAndView.addObject("errorMsg", errorMsg);
			return modelAndView;
		}
		User user = userService.selectUserById(userId);
		if(null==user){
			String errorMsg = "用户ID:" + userId + "的信息无效";
			modelAndView.addObject("errorMsg", errorMsg);
			return modelAndView;
		}
		LoginAuth loginAuth = userService.selectLoginAuthByUserId(userId);
		if(null!=loginAuth){
			modelAndView.addObject("loginAuth", loginAuth);
		}
		modelAndView.addObject("user", user);
		modelAndView.setViewName("userLoginAuth");
		return modelAndView;
	}
}
