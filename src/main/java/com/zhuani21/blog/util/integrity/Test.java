package com.zhuani21.blog.util.integrity;

import java.io.File;

import com.zhuani21.blog.util.file.FileSizeUtil;

public class Test {
	
	public static void main(String[] args) throws Exception {
		String fileStr = "F:/TDDowload";
		File root = new File(fileStr);
		calc(root);
	}
	private static void calc(File f) throws Exception{
		if(f.isDirectory()){
			File[] listFiles = f.listFiles();
			for(File subF :listFiles){
				calc(subF);
			}
		}else if(f.isFile()) {
			String fileName = f.getName();
			long fileSize = f.length();
			long start = System.currentTimeMillis();
			//String fileMsg = FastMD5.CalFileMd5(f);
			String fileMsg = CheckSystemFolderSum.checkMd5(f);
			System.out.println(fileName);
			System.out.println(FileSizeUtil.humenRead(fileSize));
			System.out.println(fileMsg);
			System.out.println("___________" + (System.currentTimeMillis() - start) + "____________________________________");
		}
	}
}
