package com.zhuani21.blog.dao;

import java.util.List;

import com.zhuani21.blog.auto.bean.Job;
import com.zhuani21.blog.bean.JobCustom;

public interface JobDao {
	int insert(Job record);

	List<JobCustom> selectJobList();
}
