package com.zhuani21.blog.bean;

import com.zhuani21.blog.auto.bean.Blog;

/**
 * Blog extension class
 * 
 * @author wgn
 */
public class BlogEx extends Blog {
	/**
	 * DEFAULT_STATUS - 默认的blog状态1-有效
	 */
	public static final String DEFAULT_STATUS = "1"; 
	/**
	 * ONE_PAGE_COUNT - 默认每次load more加载条目数
	 */
	public static final Integer ONE_PAGE_COUNT = 12;
	/**
	 * pageIndex - 页面当前的blog条目数。load more加载从当前条目数到 + 5的数据。
	 */
	private Integer pageIndex = 0;

	public Integer getOnePageCount() {
		return ONE_PAGE_COUNT;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
}
