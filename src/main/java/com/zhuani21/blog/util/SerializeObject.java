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
			//oo.writeObject(null);//避免EOFException,说实话我也不明白为啥这么操蛋，不加这句，读取的时候就会报EOF异常
			oo.flush();
			oo.close();
		} catch (IOException e) {
			throw new SerializeException("序列化出错：对象" + o + ";文件：" + f);
		}

	}
	/**
	 * 反序列化获取对象，返回类型为T的对象。
	 * 这里的unchecked注解只是为了去掉警告，事实上类型一致的责任在调用者手上，例如无法将一个Student转换为Teacher
	 * @param t - 没有使用，使用的是t的类型
	 * @param fileName - 序列化文件
	 * @return  T的一个对象
	 */
	@SuppressWarnings("unchecked")
	public static<T> T deserialize(T t,String fileName) {
		File f = new File(filePath + fileName);
		if (!f.exists()) {
			return null;
		}
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(f));
			return (T)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null!=ois){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void main(String[] a) {
		serialize(UserBlogDataMapper.getBlog("admin"),BLOG_FILE_NAME);
	}
}
