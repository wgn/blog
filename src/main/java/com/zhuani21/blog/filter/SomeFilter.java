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

public class SomeFilter implements Filter{

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
		req.getServletPath();
		
	}
	/**
	 * 设置过滤访问监控页面的用户，如果是管理员才允许访问，否则不允许。
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean filterMelodyMonitor(ServletRequest request, ServletResponse response){
		
		
		
		return true;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
