package com.zhuani21.blog.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.zhuani21.blog.bean.PropertyVO;
import com.zhuani21.blog.exception.BlogBaseException;

public class Properties extends LinkedHashMap<String, PropertyVO> {

	private static final long serialVersionUID = 3974630492400628366L;

	protected Properties defaults;

	public Properties() {
		this(null);
	}

	public Properties(Properties defaults) {
		this.defaults = defaults;
	}

	public String getProperty(String key) {
		PropertyVO propVO = this.get(key);
		if(null!=propVO){
			return propVO.getPropertyValue();
		}
	    return null;
	}
	public synchronized void load(InputStream inStream) throws IOException {
		load(new BufferedReader(new InputStreamReader(inStream)));
	}
	public synchronized void load(Reader reader) throws IOException{
		BufferedReader br = new BufferedReader(reader);
		
		String line = br.readLine();
		PropertyVO propVO = null;
		while(null!=line){
			if(null==propVO){
				propVO = new PropertyVO();
			}
			line = line.trim();
			if(StringUtils.isBlank(line)){
				//seem comment
				propVO.addComment(line);
			}else if(StringUtils.startsWith(line, "#")){
				//comment
				propVO.addComment(line);
			} else {
				if(line.contains("=")){
					//prop
					String[] mixString = line.split("=", 2);
					String key = mixString[0].trim();
					String value = mixString[1].trim();
					propVO.setPropertyName(key);
					propVO.setPropertyValue(value);
					put(key, propVO);
					propVO = null;
				}else{
					//no comment,no prop?
					throw new BlogBaseException("配置文件编写有问题");
				}
			}
			line = br.readLine();
			
		}
	}

	public void store(OutputStream out) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
		stor(bw);
	}
	public void store(Writer writer) throws IOException {
		BufferedWriter bw = (writer instanceof BufferedWriter)?(BufferedWriter)writer : new BufferedWriter(writer);
		stor(bw);
	}
	private void stor(BufferedWriter bw) throws IOException {
        synchronized (this) {
            for(String key:this.keySet()){
            	PropertyVO propVO = this.get(key);
            	for(String comment: propVO.getComments()){
            		bw.write(comment);
            		bw.newLine();
            	}
            	bw.write(propVO.getPropertyName() + "=" + propVO.getPropertyValue());
            	bw.newLine();
            }
        }
        bw.flush();
	}

	public static void main(String [] args) throws IOException{
		/*Properties props = new Properties();
		FileReader fir = new FileReader(new File("C:\\Users\\vuclip111\\Desktop\\1.txt"));
		props.load(fir);*/
		String s = "ohello world~o!o21o";
		String ss[] = s.split("o", 5);
		for(String sd: ss){
			System.out.println(sd);
		}
	}

	public boolean updateProperty(PropertyVO prop) {
		if(prop==null || null==prop.getPropertyName() || null==prop.getPropertyValue()){
			return false;
		}
		PropertyVO propVO = this.get(prop.getPropertyName());
		if(null==propVO){
			return false;
		}
		propVO.setPropertyName(prop.getPropertyName());
		propVO.setPropertyValue(prop.getPropertyValue());
		if(null!=prop.getComments()){
			propVO.setComments(prop.getComments());
		}
		put(prop.getPropertyName(), propVO);
		return true;
		
	}

	public boolean addProperty(PropertyVO prop) {
		if(prop==null || null==prop.getPropertyName() || null==prop.getPropertyValue()){
			return false;
		}
		PropertyVO propVO = new PropertyVO();
		propVO.setPropertyName(prop.getPropertyName());
		propVO.setPropertyValue(prop.getPropertyValue());
		propVO.setComments(prop.getComments());
		put(prop.getPropertyName(), propVO);
		return true;
	}
}
