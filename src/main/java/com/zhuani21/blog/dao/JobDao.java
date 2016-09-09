package com.zhuani21.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuani21.blog.auto.bean.Job;
import com.zhuani21.blog.bean.JobCustom;

public interface JobDao {
	int insert(Job record);

	List<JobCustom> selectJobList(@Param(value = "timeRange")String timeRange);
}
