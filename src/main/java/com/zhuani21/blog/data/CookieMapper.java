package com.zhuani21.blog.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.zhuani21.blog.auto.bean.User;

public class CookieMapper{
	private static Map<String,User> map  = new ConcurrentHashMap<String,User>(); 
	private static Map<Integer,String> uidMap = new ConcurrentHashMap<Integer,String>(); 
	public static final void put(String k,User user){
		String key = uidMap.get(user.getId());
		if(StringUtils.isNoneBlank(key)){
			map.remove(key);
			uidMap.remove(user.getId());
		}
		uidMap.put(user.getId(), k);
		map.put(k, user);
	}
	public static final User get(String k){
		return map.get(k);
	}
	public static final void remove(String k){
		User u = map.get(k);
		if(u!=null){
			map.remove(k);
			uidMap.remove(u.getId());
		}
	}
}
