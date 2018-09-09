package com.zhuani21.blog.util.integrity;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MD5 {
	private static final long KB = 1024;
	//public static final long MB = 1024*1024;
	private static final long MB = 1048576;
	//public static final long GB = 1024*1024*1024;
	private static final long GB = 1073741824;
	
	public static String CalFileMd5(String strFile) throws Exception{
		File file = new File(strFile);
		if ( !file.exists() ){
			throw new Exception("file not exists!");
		}
		long fileSize = file.length();
		String str = new String();
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
			FileDescriptor fd = fis.getFD();
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			int nBuffLen = 10*1024; //10KB
			byte[] buff = new byte[nBuffLen];
			List<Byte> res = new ArrayList<Byte>();
			while( true ){
				int nRead = fis.read(buff);
				if ( nRead>0 )
					res.add(buff[0]);
				if ( nRead<nBuffLen )
					break;
			}
			
			byte[] bMd5 = md5.digest();
			StringBuffer sb = new StringBuffer("0x");
			for ( int i=0; i<bMd5.length; ++i ){
				sb.append(bMd5[i]);
			}
			str = sb.toString();
		}
		catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		finally{
			if ( fis != null )
				fis.close();
		}
		return str;
	}

	private static Object ByteToHex(byte b) {
		return null;
	}

}
