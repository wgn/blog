package com.zhuani21.blog.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.exception.BlogBaseException;

public class GlobalExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		
		BlogBaseException baseEx = null;
		if(ex instanceof MaxUploadSizeExceededException){
			baseEx = new BlogBaseException("Sorry！上传文件最大为5M");
		} else if(ex instanceof BlogBaseException){
			baseEx = (BlogBaseException) ex;
		}else{
			System.out.println(ex.getMessage());
			baseEx = new BlogBaseException("Sorry！看起来好像发生了什么不愉快的事。");
		}
		String errorMsg = baseEx.getMessage();
		modelAndView.addObject("errorMsg", errorMsg);
		modelAndView.setViewName("errorMsg");
		return modelAndView;
	}

}