package com.zhuani21.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.LoginAuth;
import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.service.LoginAuthService;
import com.zhuani21.blog.util.CookieMapper;
import com.zhuani21.blog.util.WConstant;
import com.zhuani21.blog.util.WebRequestUtils;

@Controller
public class LoginAuthController {
	@Autowired
	LoginAuthService loginAuthService;
	
	
	@RequestMapping(value={"/index"})
	public ModelAndView index(HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView() ;
		User user = (User) session.getAttribute(WConstant.SESSION_LOGIN_USER);
		if(null!=user){
			modelAndView.setViewName("index");
		}
		return modelAndView;
	}
	@RequestMapping(value={"/login"},method={RequestMethod.GET})
	public ModelAndView loginGet(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		User user = null;
		//1.check session
		user = checkSession(req.getSession());
		//2,check cookie
		if(null==user){
			user = checkCookie(req);
		}
		if(null==user){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		dealCookieAndSession(req,resp,user);

		modelAndView.setViewName("redirect:/blog/admin");
		return modelAndView;
	}
	
	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public ModelAndView loginPost(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		User user = null;
		//1.check session
		user = checkSession(req.getSession());
		//2,check cookie
		if(null==user){
			user = checkCookie(req);
		}
		//3,check username & password
		if(null==user){
			user = checkUser(req);
		}
		if(null==user){
			modelAndView.addObject("username",StringUtils.trimToEmpty(req.getParameter("username")));
			modelAndView.addObject("loginErrorMsg", "用户名或密码错误");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		dealCookieAndSession(req,resp,user);

		modelAndView.setViewName("redirect:/blog/admin");
		return modelAndView;
	}

	private void dealCookieAndSession(HttpServletRequest req, HttpServletResponse resp, User user) {
		HttpSession session = req.getSession();
		session.setAttribute(WConstant.SESSION_LOGIN_USER, user);
		String remember = req.getParameter("remember");
		if("1".equals(remember)){
			//保存cookie一周
			String sessionId = session.getId();
			String ip = WebRequestUtils.getIp(req);
			if(StringUtils.isNoneBlank(ip) && StringUtils.isNotBlank(sessionId)){
				addCookie(resp,WConstant.COOKIE_LOGIN_KEY,session.getId());
				CookieMapper.put(ip+sessionId, user);
			}
		}		
	}


	private User checkUser(HttpServletRequest req) throws Exception {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(StringUtils.isBlank(username) && StringUtils.isBlank(password)){
			return null;
		}
		return loginAuthService.findUserByUsernamePassword(username, password);
	}

	private User checkCookie(HttpServletRequest req) {
		Cookie cookie = getCookieByName(req,WConstant.COOKIE_LOGIN_KEY);
		if(null!=cookie){
			String value = cookie.getValue();
			String ip = WebRequestUtils.getIp(req);
			if(StringUtils.isNoneBlank(value) && StringUtils.isNoneBlank(ip)){
				User user = CookieMapper.get(ip + value);
				/*if(null!=user){
					CookieMapper.remove(ip + value);
				}*/
				return user;
			}
		}
		return null;
	}


	private User checkSession(HttpSession session) {
		return (User) session.getAttribute(WConstant.SESSION_LOGIN_USER);
	}
	
	@RequestMapping(value={"/logout"}, method={RequestMethod.GET})
	public String logout(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		//销毁session
		req.getSession().invalidate();
		//销毁cookie
		Cookie[] cookies = req.getCookies();
		if (cookies!=null){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(WConstant.COOKIE_LOGIN_KEY)) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath("/");
					resp.addCookie(cookie);
					break;
				}
			}
		}
		return "redirect:login";
	}
	
	private void addCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name.trim(), value.trim());
		cookie.setMaxAge(60 * 60 * 24 * 7);// 设置为7天
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	private  Cookie getCookieByName(HttpServletRequest request,String name){
	        Map<String,Cookie> cookieMap = ReadCookieMap(request);
	        if(cookieMap.containsKey(name)){
	            Cookie cookie = (Cookie)cookieMap.get(name);
	            return cookie;
	    }else{
	         return null;
	    }   
	}
	private  Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
