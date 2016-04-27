package com.zhuani21.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.util.WConstant;
import com.zhuani21.blog.util.WebRequestUtils;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute(WConstant.SESSION_LOGIN_USER);
		
		if(null==user){
			user = WebRequestUtils.getUserFromCookie(request);
			
			session.setAttribute(WConstant.SESSION_LOGIN_USER, user);
		}
		
		if(null!=user){
			return true;
		}

		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/login");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
