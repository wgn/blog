package com.zhuani21.blog.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {
	private static Logger logger = Logger.getLogger(StringToDateConverter.class); 
	@Override
	public Date convert(String source) {
		String pattern = null;
		SimpleDateFormat sdf =  null;
		if(StringUtils.isBlank(source)){
			return null;
		}
		if(source.indexOf("-")>0){
			switch (source.length()) {
			case 10:
				pattern = "yyyy-MM-dd";
				break;
			case 13:
				pattern = "yyyy-MM-dd HH";
				break;
			case 16:
				pattern = "yyyy-MM-dd HH:mm";
				break;
			default:
				pattern = "yyyy-MM-dd HH:mm:ss";
				break;
			}
		}else if(source.indexOf("/")>0){
			switch (source.length()) {
			case 10:
				pattern = "yyyy/MM/dd";
				break;
			case 13:
				pattern = "yyyy/MM/dd HH";
				break;
			case 16:
				pattern = "yyyy/MM/dd HH:mm";
				break;
			default:
				pattern = "yyyy/MM/dd HH:mm:ss";
				break;
			}
		}else{
			switch (source.length()) {
			case 8:
				pattern = "yyyyMMdd";
				break;
			case 11:
				pattern = "yyyyMMdd HH";
				break;
			case 14:
				pattern = "yyyyMMdd HH:mm";
				break;
			default:
				pattern = "yyyyMMdd HH:mm:ss";
				break;
			}
		}
		
		sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			logger.error("前台日期格式转换bean属性出错", e);
			throw new RuntimeException(e);
		}
	}
}
