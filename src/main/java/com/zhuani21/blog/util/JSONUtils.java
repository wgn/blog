package com.zhuani21.blog.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONUtils {

	public static void main(String[] args) {

		File f = new File("C:/Users/ppd/Desktop/temp/sql/mysql/xdd_p.json");

		List<String> listValue = listValue(f,"sdhoto_path");
		String baseUrl = "https://ss0.bdstatic.com/";
		for(String subUrl: listValue){
			FileDownload.download(baseUrl + subUrl);
		}

	}
	
	public static List<String> listValue(File jsonFile,String fieldName){
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(jsonFile);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listValue(root,fieldName);
	}

	/**
	 * 寻找node节点下所有fieldName的值
	 * @param node
	 * @param fieldName
	 * @return 
	 */
	public static List<String> listValue(JsonNode node,String fieldName){
		List<String> fieldValues = new ArrayList<String>();
		if(null==node){
			return fieldValues;
		}
		if(node.isArray()){
			for(int i =0 ;i<node.size();i++){
				Iterator<Entry<String, JsonNode>> fields = node.get(i).getFields();
				while(fields.hasNext()){
					Entry<String, JsonNode> next = fields.next();
					String nextName = next.getKey();
					JsonNode nextNode = next.getValue();
					if(fieldName.equals(nextName)){
						fieldValues.add((nextNode.asText()));
					}else{
						fieldValues.addAll(listValue(nextNode,fieldName));
					}
				}
			}
		}else {
			Iterator<Entry<String, JsonNode>> fields = node.getFields();
			while(fields.hasNext()){
				Entry<String, JsonNode> next = fields.next();
				String nextName = next.getKey();
				JsonNode nextNode = next.getValue();
				if(fieldName.equals(nextName)){
					fieldValues.add((nextNode.asText()));
				}else{
					fieldValues.addAll(listValue(nextNode,fieldName));
				}
			}
		}
		return fieldValues;
	}

}
