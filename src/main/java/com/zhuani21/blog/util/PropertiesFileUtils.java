package com.zhuani21.blog.util;

import java.io.File;
/*
操作属性文件，可以为我们的程序带来更方便的移植性，下面是一个示例，可以读、写、更改属性
读采用了两种方式，一种是采用Properties类，另外一种是采用资源绑定类ResourceBundle类，
下面是源程序，里面有详细的注释：
*/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;

import com.zhuani21.blog.bean.PropertyVO;

/**
 * 对属性文件（xx.properties）的操作 注：属性文件一定要放在当前工程的根目录下，也就是放在与src目录在同一个目录下（我的JDevelop
 * 是这样的）
 */
public class PropertiesFileUtils {
	
	public static boolean addProperty(String propFile,PropertyVO prop){
		return updateProperty(propFile,prop,"add");
	}
	public static String getProperty(String propFile,String propName){
		String s = "";
		Properties p = new Properties();// 加载属性文件读取类
		InputStream in;
		try {
			// propertiesFileName如test.properties
			in =  Resources.getResourceAsStream(propFile);// 以流的形式读入属性文件
			p.load(in);// 属性文件将该流加入的可被读取的属性中
			in.close();// 读完了关闭
			s = p.getProperty(propName);// 取得对应的属性值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	public static boolean updateProperty(String propFile,PropertyVO prop){
		return updateProperty(propFile,prop,"update");
	}
	private static boolean updateProperty(String propFile,PropertyVO prop,String opType){
		boolean writeOK = true;
		Properties p = new Properties();
		InputStream in;
		File propertiesFile;
		try {
			propertiesFile = Resources.getResourceAsFile(propFile);
			in =  new FileInputStream(propertiesFile);
			p.load(in);//
			in.close();
			if("update".equals(opType)){
				p.updateProperty(prop);
			}else if("add".equals(opType)){
				p.addProperty(prop);
			}
			FileOutputStream out = new FileOutputStream(propertiesFile);// 输出流
			p.store(out);// 设置属性头，如不想设置，请把后面一个用""替换掉
			out.flush();// 清空缓存，写入磁盘
			out.close();// 关闭输出流
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeOK;
	}
	
	public static void main(String[] args) {
		String p = PropertiesFileUtils.getProperty("sys.properties", "test.ttt.soi.oisd");
		System.out.println(p);
		PropertyVO propVO = new PropertyVO();
		propVO.setPropertyName("test.ttt.soi.oisd");
		propVO.setPropertyValue("18");
		//boolean res = PropertiesFileUtils.updateProperty("sys.properties", propVO);
		PropertyVO propVO1 = new PropertyVO();
		propVO1.setPropertyName("lmm");
		propVO1.setPropertyValue("xiaohuaidan");
		List<String> comments = new ArrayList<String>();
		comments.add("# xiao ya tou zhen shi tai huai le ~_~ !");
		propVO1.setComments(comments);
		boolean res1 = PropertiesFileUtils.addProperty("sys.properties", propVO1);
		System.out.println(res1);
		//System.out.println(res);
	}
}
