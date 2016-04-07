package com.zhuani21.blog.dao;

import java.util.List;

import com.zhuani21.blog.auto.bean.Blog;
import com.zhuani21.blog.bean.BlogEx;

public interface BlogDao {
	/**
	 * 根据条件（用户、状态）获取博客列表（指定的开始位置和条目数）
	 * @param blogEx 这个复合对象中只有部分字段在此方法中使用到：
	 * 				userId、status、pageIndex、onePageCount。
	 * 				前两个为查询条件。后两个为结果条目开始index和要获取的条目总数。
	 * @return Blog List
	 */
	public List<Blog> getBlogList(BlogEx blogEx);
}
