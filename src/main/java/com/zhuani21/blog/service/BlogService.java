package com.zhuani21.blog.service;

import java.util.List;

import com.zhuani21.blog.auto.bean.Blog;


public interface BlogService {
	
	/**
	 * 获取指定用户的博客列表，从pageIndex开始到BlogEx.ONE_PAGE_COUNT条记录
	 * @param userId - 用户ID
	 * @param pageIndex - 记录开始的index
	 * @return BlogList
	 */
	public List<Blog> getBlogList(Integer userId,Integer pageIndex);
	
	public void addBlog(Blog blog);

}
