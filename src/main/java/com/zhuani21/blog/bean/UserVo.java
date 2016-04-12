package com.zhuani21.blog.bean;

import java.util.Date;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.util.WDate;

public class UserVo extends User {
	public String getBirthdayStr(){
		Date birthday = this.getBirthday();
		if(null!=birthday){
			return new WDate(birthday,"yyyy-MM-dd").toString();
		}
		return null;
	}
}
