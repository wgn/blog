package com.zhuani21.blog.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import com.zhuani21.blog.data.UserBlogDataMapper;
import com.zhuani21.blog.exception.SerializeException;

public class SerializeObject {
	private static String filePath;
	public static final String BLOG_FILE_NAME = "blog_file.txt";

	static {
		URL url1 = SerializeObject.class.getResource("/");
		filePath = url1.getFile() + "file/blog/";
	}
	public static String getBlogFilePath(){
		return filePath;
	}

	public static void serialize(Object o, String fileName) {
		File f = null;
		try {
			f = new File(filePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File(filePath+fileName)));
			oo.writeObject(o);
			oo.close();
		} catch (IOException e) {
			throw new SerializeException("序列化出错：对象" + o + ";文件：" + f);
		}

	}

	public static<T> T deserialize(T t,String fileName) {
		File f = new File(filePath + fileName);
		if (!f.exists()) {
			return null;
		}
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(f));
			return (T)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] a) {
		serialize(UserBlogDataMapper.getBlog("admin"),BLOG_FILE_NAME);
	}
}
