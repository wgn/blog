package com.zhuani21.blog.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.zhuani21.blog.exception.BlogBaseException;

public class GlobalExceptionHandler implements HandlerExceptionResolver {
	
	private static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		
		BlogBaseException baseEx = null;
		if(ex instanceof MaxUploadSizeExceededException){
			baseEx = new BlogBaseException("Sorry！上传文件最大为5M");
		}else if(ex instanceof DuplicateKeyException){
			baseEx = new BlogBaseException("Sorry！数据库操作发生唯一（主键）约束冲突。");
		} else if(ex instanceof BlogBaseException){
			baseEx = (BlogBaseException) ex;
		}else{
			logger.error(ex.getMessage(),ex);
			baseEx = new BlogBaseException("Sorry！看起来好像发生了什么不愉快的事。");
		}
		String errorMsg = baseEx.getMessage();
		modelAndView.addObject("errorMsg", errorMsg);
		modelAndView.setViewName("errorMsg");
		return modelAndView;
	}

}