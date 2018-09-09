package com.zhuani21.blog.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;

public class FileSizeUtil {
	
	public static final long KB = 1024;
	//public static final long MB = 1024*1024;
	public static final long MB = 1048576;
	//public static final long GB = 1024*1024*1024;
	public static final long GB = 1073741824;
	
	public static final BigDecimal TB = new BigDecimal(1024*1024).multiply(new BigDecimal(1024*1024));


	/**
	 * 获取文件长度
	 * 
	 * @param file
	 * @return
	 */
	public static long getSize(File file) {
		if (file.exists() && file.isFile()) {
			return file.length();
		}
		return 0;
	}

	/**
	 * 根据java.io.*的流获取文件大小,
	 * 超过2G的文件不能使用此方法。
	 * 
	 * @param file
	 * @return
	 */
	public static int getFileSize2(File file) {
		FileInputStream fis = null;
		try {
			if (file.exists() && file.isFile()) {
				fis = new FileInputStream(file);
				return fis.available();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	/**
	 * 根据java.nio.*的流获取文件大小
	 * 
	 * @param file
	 * @return
	 */
	public static long getFileSize3(File file) {
		FileChannel fc = null;
		FileInputStream fis = null;
		try {
			if (file.exists() && file.isFile()) {
				fis = new FileInputStream(file);
				fc = fis.getChannel();
				return fc.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fc) {
				try {
					fis.close();
					fc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public static String humenRead(long size) {
		double res = 0;
		String unit = "B";
		if (size > 1024) {
			long kb = size / 1024;
			if (kb > 1024) {
				long mb = kb / 1024;
				if (mb > 1024) {
					res = size / 1024.0 / 1014.0 / 1024.0;
					unit = "GB";
				} else {
					res = size / 1024.0 / 1014.0;
					unit = "MB";
				}
			} else {
				res = size / 1024.0;
				unit = "KB";
			}
		} else {
			res = size;
			unit = "B";
		}
		BigDecimal big = new BigDecimal(res);
		//big.setScale(2, BigDecimal.ROUND_HALF_UP);
		return big.setScale(2, BigDecimal.ROUND_HALF_UP) + " " + unit;
	}
}
