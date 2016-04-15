package com.zhuani21.blog.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigReader {
	private static Logger logger = Logger.getLogger(ConfigReader.class); 
	public static Properties classpathLoad(String filename){
		Properties prop = new Properties();  
		InputStream inStream = ClassLoader.getSystemResourceAsStream(filename); 
		try {
			prop.load(inStream);
		} catch (IOException e) {
			logger.error("配置文件：" + filename + "。读取出错。",e);
			throw new RuntimeException(e);
		}  
		return prop;
	}
	public static String classpathPropertyLoad(String filename,String property){
		Properties prop = classpathLoad(filename);
		return prop.getProperty(property);
	}
	public static void main(String[] args){
		String logFile = ConfigReader.classpathPropertyLoad("log4j.properties", "log4j.appender.FILE.File");
		//子目录不要忘记点啊"."
		//Properties prop1 = ConfigReader.classpathLoad("./file/log4j.properties");
		int e = logFile.lastIndexOf("/");
		String blogPath = logFile.substring(0, e);
		System.out.println(blogPath);
		e = blogPath.lastIndexOf("/");
		String tomcatLogPath = blogPath.substring(0, e);
		System.out.println(tomcatLogPath);
	}
}
