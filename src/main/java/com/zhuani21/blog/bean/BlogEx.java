package com.zhuani21.blog.bean;

import com.zhuani21.blog.auto.bean.Blog;

/**
 * Blog extension class
 * 
 * @author wgn
 */
public class BlogEx extends Blog {
	
	public static final String DEFAULT_STATUS = "1"; 
	//每页数，默认先设置为100
	private static final Integer PAGE_COUNT = 5;
	//分页
	private Integer page = 0;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageCount() {
		return PAGE_COUNT;
	}
	/**
	 * 计算得到的
	 * @return 开始的记录
	 */
	public Integer getStartIndex() {
		return PAGE_COUNT*page;
	}
}
