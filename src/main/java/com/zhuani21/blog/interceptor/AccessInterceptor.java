package com.zhuani21.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.data.IpActionMapper;
import com.zhuani21.blog.util.WConstant;
import com.zhuani21.blog.util.WebRequestUtils;
/**
 * 限制用户访问的频率
 * @author wgn
 */
public class AccessInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User u = (User) request.getSession().getAttribute(WConstant.SESSION_LOGIN_USER);
		String ip = WebRequestUtils.getIp(request);
		if(u!=null){
			if(!IpActionMapper.uaccess(ip+u.getId())){
				return false;
			}
		} else{
			if(!IpActionMapper.access(ip)){
				return false;
			}
		}
		return true;
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
