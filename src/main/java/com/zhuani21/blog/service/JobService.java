package com.zhuani21.blog.service;

import java.util.List;

import com.zhuani21.blog.auto.bean.Job;
import com.zhuani21.blog.bean.JobCustom;

public interface JobService {
	public List<Job> queryJobList() throws Exception;

	public JobCustom queryJobById(Integer id);

	public void insertJob(JobCustom job);

	public void updateJob(JobCustom job);

	public String findOriginalFileNameByFilePath(String name);

	public int deleteJobById(Integer id);
}
