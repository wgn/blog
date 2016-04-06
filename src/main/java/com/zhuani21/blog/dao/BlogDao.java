package com.zhuani21.blog.dao;

import java.util.List;

import com.zhuani21.blog.auto.bean.Blog;
import com.zhuani21.blog.bean.BlogEx;

public interface BlogDao {
	public List<Blog> getBlogList(BlogEx blogEx);
}
