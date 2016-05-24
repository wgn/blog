package com.zhuani21.blog.bean;

import java.util.HashMap;
import java.util.Map;

public class DebugInfoVO {
	
	private Map<String,String> props;

	public Map<String, String> getProps() {
		if(null==props){
			props = new HashMap<String,String>();
		}
		return props;
	}

	public void setProps(Map<String, String> props) {
		this.props = props;
	}
	
}
