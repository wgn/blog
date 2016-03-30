package com.zhuani21.blog.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.Job;
import com.zhuani21.blog.auto.mapper.JobMapper;
import com.zhuani21.blog.bean.JobCustom;
import com.zhuani21.blog.service.JobService;
@Service("jobService")
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobMapper jobMapper;

	@Override
	public List<Job> queryJobList() throws Exception {
		return jobMapper.selectByExample(null);
	}

	@Override
	public JobCustom queryJobById(Integer id) {
		Job job =  jobMapper.selectByPrimaryKey(id);
		JobCustom jobCustom = new JobCustom();
		BeanUtils.copyProperties(job, jobCustom);
		return jobCustom;
	}

	@Override
	public void insertJob(JobCustom job) {
		jobMapper.insert(job);
	}

}
