package com.zhuani21.blog.bean;

public class BlogJsonVO {

	private Integer pageIndex;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	@Override
	public String toString() {
		return "BlogJsonVO [pageIndex=" + pageIndex + ", content=" + content + "]";
	}

}
