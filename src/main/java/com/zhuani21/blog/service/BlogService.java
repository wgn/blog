package com.zhuani21.blog.service;

import java.util.List;

import com.zhuani21.blog.auto.bean.Blog;


public interface BlogService {
	
	public List<Blog> getBlogList(Integer userId,Integer page);
	
	public void addBlog(Blog blog);

}
