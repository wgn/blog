package com.zhuani21.blog.bean.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zhuani21.blog.auto.bean.LoginAuth;
import com.zhuani21.blog.util.RegexUtils;

public class LoginAuthValidator {
	public static List<String> validate(LoginAuth target){
		List<String> list = new ArrayList<String>();
		
		if(target==null){
			list.add("null对象");
		}else{
			if(null==target.getUserId()){
				list.add("用户ID为空");
			}
			//username check
			if(StringUtils.isBlank(target.getUsername())){
				list.add("用户名为空");
			}else{
				if(!RegexUtils.checkUsername(target.getUsername(), 3, 20)){
					list.add("用户名长度为3-20位英文字母下划线或数字");
				}
			}
			//password
			if(StringUtils.isBlank(target.getPassword())){
				list.add("密码为空");
			}else{
				if(target.getPassword().length()<1|| target.getPassword().length()>20){
					list.add("密码长度为1-20位");
				}
			}
		}
		
		if(list.size()==0){
			return null;
		}
		return list;
	}
	
}
