package com.zhuani21.blog.debug;

import java.util.List;

public class BlogDebugBean extends DebugBean{
	private String blogDirPath = null;
	private boolean blogDirExist = false;
	private List<String> blogFileNameList = null;
	public String getBlogDirPath() {
		return blogDirPath;
	}
	public void setBlogDirPath(String blogDirPath) {
		this.blogDirPath = blogDirPath;
	}
	public boolean isBlogDirExist() {
		return blogDirExist;
	}
	public void setBlogDirExist(boolean blogDirExist) {
		this.blogDirExist = blogDirExist;
	}
	public List<String> getBlogFileNameList() {
		return blogFileNameList;
	}
	public void setBlogFileNameList(List<String> blogFileNameList) {
		this.blogFileNameList = blogFileNameList;
	}
}
