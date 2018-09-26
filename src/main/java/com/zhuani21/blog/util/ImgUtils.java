package com.zhuani21.blog.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ImgUtils {
	//应用相对目录
	private static String localDir = "img";
	
	private static File dirFile = new File(localDir);
	static {
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
	}
	public static void setSaveDir(String dir){
		dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
	}

	public static int download(String url) throws IOException{
		
		File f = HttpClient.doGet(url,dirFile.getAbsolutePath() + "/1.jpg");
	
		return 0;
	}
	
	public static int download(List<String> urls){
		return 0;
	}
	
	public static int download(String basePath , List<String> urls){
		return 0;
	}
	
	public static void main(String [] args) throws IOException{
		download("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_86d58ae1.png");
	}
}
