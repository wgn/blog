package com.zhuani21.blog.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.zhuani21.blog.auto.bean.User;

public class BeanEqualsUtils {
	public static <T> boolean equals(T t1,T t2) throws Exception{
		if(t1==null || t2==null){
			return false;
		}
		StringBuffer sb1 =  getObjString(t1);
		StringBuffer sb2 =  getObjString(t2);
		if(sb1.toString().equals(sb2.toString())){
			return true;
		}
		
		return false;
	}
	private static <T> StringBuffer getObjString(T t) throws IllegalAccessException, InvocationTargetException {
		StringBuffer sb;
		sb = new StringBuffer();
		Method[] methods = t.getClass().getMethods();
		if(null!=methods){
			for(Method method :methods){
				String mname = method.getName();
				if(mname.startsWith("get")){
					sb.append(mname).append("#").append(method.invoke(t, null));
				}
			}
		}
		return sb;
	}
	public static void main(String [] args) throws Exception{
		User u1 = new User();
		u1.setNickname("u1");
		User u2 = new User();
		u2.setNickname("u1");
		System.out.println(BeanEqualsUtils.equals(u1, u2));
	}
}
