package com.zhuani21.blog.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zhuani21.blog.bean.BlogCustom;
import com.zhuani21.blog.util.SerializeObject;

public class UserBlogDataMapper {

	private static Map<String, BlogCustom> blogMap = new HashMap<String, BlogCustom>();

	static {
		String blogFilePath = SerializeObject.getBlogFilePath();
		String fileName = "admin." + SerializeObject.BLOG_FILE_NAME;
		File dir = new File(blogFilePath);
		// File dir = new File("\\C:\\Users\\vuclip111\\Desktop");
		if (dir.isDirectory()) {
			String[] fileNames = dir.list();
			for (String fName : fileNames) {
				String[] splitNames = fName.split("\\.");
				String username = splitNames[0];
				if(StringUtils.isNotBlank(username) && fName.indexOf(SerializeObject.BLOG_FILE_NAME)!=-1){
					BlogCustom blogCustom = SerializeObject.deserialize(new BlogCustom(), fName);
					if(null!=blogCustom){
						blogMap.put(username, blogCustom);
					}
				}
			}
			// dir.listFiles();
		}
	}

	public static void main(String[] a) {
		System.out.println();
	}

	public static BlogCustom getBlog(String username) {
		BlogCustom b = blogMap.get(username);
		if (null == b) {
			b = new BlogCustom();
			blogMap.put(username, b);
			System.out.println("new a blog for user:" + username);
		}
		return b;
	}
}
