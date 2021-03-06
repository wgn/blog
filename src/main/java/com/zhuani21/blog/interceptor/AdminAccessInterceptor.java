package com.zhuani21.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.util.AuthorizationChecker;
import com.zhuani21.blog.util.WConstant;

public class AdminAccessInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = Logger.getLogger(AdminAccessInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute(WConstant.SESSION_LOGIN_USER);
		if(AuthorizationChecker.isAdmin(user)){
			return true;
		}
		if(user!=null){
			logger.info("Permission denied. user id:" + user.getId() +" , access url:" + request.getRequestURL());
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/login");
		}
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
