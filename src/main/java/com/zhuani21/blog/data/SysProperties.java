package com.zhuani21.blog.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

import com.zhuani21.blog.exception.BlogBaseException;

public class SysProperties {
	private static Properties sysProps;
	static{
		load();
	}
	public static String get(String key){
		return sysProps.getProperty(key);
	}
	private static void load(){
		sysProps = new Properties();
		InputStream is;
		try {
			is = Resources.getResourceAsStream("sys.properties");
			sysProps.load(is);
		} catch (IOException e) {
			throw new BlogBaseException("加载系统文件sys.properties出错");
		}
	}
	public static void reloadSysProperties(){
		load();
	}
	public static void main(String [] arges) throws IOException{
		System.out.println(get("CookieMapperModel"));
	}
}
