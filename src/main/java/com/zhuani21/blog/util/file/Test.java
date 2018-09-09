package com.zhuani21.blog.util.file;

import java.io.File;

public class Test {
	
	public static void main(String[] args) {
		String fileStr = "F:/TDDowload/119_karla_kush_full-hd_1080p.mp4";
		File file = new File(fileStr);
		
		long t1 = System.currentTimeMillis();
		long fileSize1 = FileSizeUtil.getSize(file);
		long t2 = System.currentTimeMillis();
		int fileSize2 = FileSizeUtil.getFileSize2(file);
		long t3 = System.currentTimeMillis();
		long fileSize3 = FileSizeUtil.getFileSize3(file);
		long t4 = System.currentTimeMillis();
		
		System.out.println(t2 - t1 + "ms res=" +  FileSizeUtil.humenRead(fileSize1));
		System.out.println(t3 - t2 + "ms res=" +  FileSizeUtil.humenRead(fileSize2));
		System.out.println(t4 - t3 + "ms res=" +  FileSizeUtil.humenRead(fileSize3));
		System.out.println(t2 - t1 + "ms res=" +  (fileSize1));
		System.out.println(t3 - t2 + "ms res=" +  (fileSize2));
		System.out.println(t4 - t3 + "ms res=" +  (fileSize3));
		
		System.out.println(FileSizeUtil.KB);
		System.out.println(FileSizeUtil.MB);
		System.out.println(FileSizeUtil.GB);
		System.out.println(FileSizeUtil.TB);
		
	}
}
