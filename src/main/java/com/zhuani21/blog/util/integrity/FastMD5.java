package com.zhuani21.blog.util.integrity;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.zhuani21.blog.util.HexUtil;

public class FastMD5 {
	private static final long KB = 1024;
	//public static final long MB = 1024*1024;
	private static final long MB = 1048576;
	//public static final long GB = 1024*1024*1024;
	private static final long GB = 1073741824;
	//不管文件多大，转换为5M计算MD5。5*MB
	private static final int GBmd5 = 102400; //100K
	private static final int _100MBmd5 = 10240; //10K
	private static final int _10MBmd5 = 1024; //1K
	private static int getBuffer(long fileSize) {
		if(fileSize>GB){
			return GBmd5;
		}else if(fileSize>(MB*100)){
			return _100MBmd5;
		}else if(fileSize>(MB*10)){
			return _10MBmd5;
		}else{
			return 0;
		}
		
	}
	public static String CalFileMd5(File file) throws Exception{
		if (null==file || !file.exists() ){
			throw new Exception("file not exists!");
		}
		long fileSize = file.length();
		byte[] fileSizebytes = String.valueOf(fileSize).getBytes();
		int bufferSize = getBuffer(fileSize);
		
		String str = new String();
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			int nBuffLen = 10240; //10KB
			byte[] buff = null;
			if(bufferSize>0){
				buff = new byte[bufferSize];
			}else{
				buff = new byte[nBuffLen];
			}
			List<Byte> res = new ArrayList<Byte>();
			while( true ){
				int nRead = fis.read(buff);
				if ( nRead>0 )
					if(bufferSize>0){
						res.add(buff[0]);
					}else{
						md5.update(buff);
					}
				if ( nRead < nBuffLen )
					break;
			}
			if(bufferSize>0){
				Byte[] resArray = res.toArray(new Byte[res.size()]);
				byte[] primitive = ArrayUtils.toPrimitive(resArray);
				md5.update(primitive);
			}
			
			byte[] bMd5 = md5.digest();
			md5.update(fileSizebytes);
			md5.update(fileSizebytes,0,fileSizebytes.length);
			byte[] nbMd5 = md5.digest();
			byte[] digest = md5.digest(fileSizebytes);
			
			System.out.println("1:" +  HexUtil.bytesToHex(bMd5));
			System.out.println("2:" +  HexUtil.bytesToHex(nbMd5));
			System.out.println("3:" +  HexUtil.bytesToHex(digest));
			
			StringBuffer sb = new StringBuffer("");
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
