package com.zhuani21.blog.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class FileDownload {
    public static void main(String[] args) {
        String url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_86d58ae1.png";
        
        download(url);
    }
    
    public static void download(List<String> fileUrlList,String dir,String prefix,String suffix){
    	if(null!= fileUrlList){
    		for(String fileUrl : fileUrlList){
    			download(fileUrl,dir,prefix,suffix);
    		}
    	}
    }
    
    public static void download(String fileUrl,String dir,String prefix,String suffix){
    	String[]  split = fileUrl.split("/");
    	String fileName = split[split.length-1];
    	String defaultDir = "img";//应用目录下的
    	if(StringUtils.isNotBlank(dir)){
    		defaultDir = dir;
    	}
    	download(fileUrl,defaultDir,prefix + fileName + suffix);
    }
    
    public static void download(List<String> fileUrlList,String dir){
    	
    	if(null!= fileUrlList){
    		for(String fileUrl : fileUrlList){
    			String srcDir = "img";//应用目录下的
    			if(StringUtils.isNotBlank(dir)){
    				srcDir = dir;
    			}
    			download(fileUrl,srcDir);
    		}
    	}
    }
    
    public static void download(List<String> fileUrlList){
    	if(null!= fileUrlList){
    		for(String fileUrl : fileUrlList){
    			download(fileUrl);
    		}
    	}
    }
    
    public static void download(String fileUrl){
    	String[]  split = fileUrl.split("/");
    	String fileName = split[split.length-1];
    	String dir = "img";//应用目录下的
    	download(fileUrl,dir,fileName);
    }
    
    public static void download(String fileUrl,String dir){
    	String[]  split = fileUrl.split("/");
    	String fileName = split[split.length-1];
    	download(fileUrl,dir,fileName);
    }
    //链接url下载图片
    public static void download(String fileUrl,String dir,String newFileName) {
    	File d = new File(dir);
    	if(! d.exists()){
    		d.mkdirs();
    	}
        URL url = null;
        try {
            url = new URL(fileUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            String imageName =  dir + File.separator +  newFileName;

            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}