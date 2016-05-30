package com.zhuani21.blog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.util.WConstant;

public class SomeFilter implements Filter{
	
	//private static Logger logger = Logger.getLogger(SomeFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			chain.doFilter(request, response);
			return;
		}
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if(!filterMelodyMonitor(req)){
			String contextPath = req.getContextPath();
			resp.sendRedirect(contextPath + "/login");
			return;
		}
		chain.doFilter(request, response);
	}
	/**
	 * 设置过滤访问监控页面的用户，如果是管理员才允许访问，否则不允许。
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean filterMelodyMonitor(HttpServletRequest req)
			throws IOException, ServletException {
		String path = req.getServletPath();
		 
		if(path.startsWith("/monitoring")){
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute(WConstant.SESSION_LOGIN_USER);
			if(null!=user && 1==user.getId()){
				return true;
			}else {
				return false;
			}
		}
		return true;
	}
	
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
