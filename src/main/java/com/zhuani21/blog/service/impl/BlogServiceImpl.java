package com.zhuani21.blog.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.Blog;
import com.zhuani21.blog.auto.mapper.BlogMapper;
import com.zhuani21.blog.bean.BlogEx;
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
		//check and init page
		if(null==page || page<0){
			page = 0;
		}
		//check userId.if userId is invalid, return empty list;
		if(null==userId){
			return Collections.emptyList();
		}
		
		BlogEx blogEx = new BlogEx();
		blogEx.setUserId(userId);
		blogEx.setPage(page);
		blogEx.setStatus(BlogEx.DEFAULT_STATUS);
		return blogDao.getBlogList(blogEx);
	}

	@Override
	public void addBlog(Blog blog) {
		blogMapper.insertSelective(blog);
	}

}
