package com.zhuani21.blog.service;

import java.util.List;

import com.zhuani21.blog.auto.bean.Job;
import com.zhuani21.blog.bean.JobCustom;

public interface JobService {
	public List<Job> queryJobList() throws Exception;

	public JobCustom queryJobById(Integer id);

	public void insertJob(JobCustom job);
}
