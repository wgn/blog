package com.zhuani21.blog.bean;

import java.util.ArrayList;
import java.util.List;

public class PropertyVO {
	private String propertyName;
	private String propertyValue;
	private List<String> comments;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	public void addComment(String comment){
		if(null==comments){
			comments = new ArrayList<String>();
		}
		comments.add(comment);
	}
	public String getCommentString(){
		if(null==comments){
			return null;
		}
		StringBuffer csb = new StringBuffer();
		for(String comment : comments){
			csb.append(comment).append(System.getProperty("line.separator"));
		}
		return csb.toString();
	}
	public List<String> getComments(){
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

}
