package com.zhuani21.blog.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.zhuani21.blog.bean.Blog;
import com.zhuani21.blog.util.SerializeObject;

public class UserBlogDataMapper {

	private static Map<String, Blog> blogMap = new HashMap<String, Blog>();

	static {
		String blogFilePath = SerializeObject.getBlogFilePath();
		String fileName = "admin." + SerializeObject.BLOG_FILE_NAME;
		File dir = new File(blogFilePath);
		//File dir = new File("\\C:\\Users\\vuclip111\\Desktop");
		if(dir.isDirectory()){
			String[] fileNames = dir.list();
			for(String fName : fileNames){
				String[] splitNames = fName.split("\\.");
				String username = splitNames[0];
				Blog blog = SerializeObject.deserialize(new Blog(),fName);
				blogMap.put(username, blog);
			}
			//dir.listFiles();
		}
	}
	
	public static void main(String[] a) {
		System.out.println();
	}

	public static Blog getBlog(String username) {
		Blog b = blogMap.get(username);
		if (null == b) {
			b = new Blog();
			blogMap.put(username, b);
			System.out.println("new a blog for user:" + username);
		}
		return b;
	}
}
