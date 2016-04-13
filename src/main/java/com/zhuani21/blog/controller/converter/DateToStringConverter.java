package com.zhuani21.blog.controller.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateToStringConverter implements Converter<Date, String> {

	@Override
	public String convert(Date source) {
		if(source==null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(source);
	}
}
