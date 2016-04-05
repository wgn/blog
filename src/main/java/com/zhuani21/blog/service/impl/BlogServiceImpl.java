package com.zhuani21.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.Blog;
import com.zhuani21.blog.auto.mapper.BlogMapper;
import com.zhuani21.blog.dao.BlogDao;
import com.zhuani21.blog.service.BlogService;
@Service("blogService")
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogMapper blogMapper;
	@Autowired
	private BlogDao blogDao;
	@Override
	public List<Blog> getBlogList(Integer userId, Integer page) {
		return null;
	}

	@Override
	public void addBlog(Blog blog) {
		blogMapper.insert(blog);
	}

}
