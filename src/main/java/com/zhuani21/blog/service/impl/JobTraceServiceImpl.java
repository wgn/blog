package com.zhuani21.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.JobTrace;
import com.zhuani21.blog.auto.bean.JobTraceExample;
import com.zhuani21.blog.auto.mapper.JobTraceMapper;
import com.zhuani21.blog.service.JobTraceService;

@Service("JobTraceService")
public class JobTraceServiceImpl implements JobTraceService {

	@Autowired
	JobTraceMapper jobTraceMapper;

	@Override
	public List<JobTrace> selectJobTraceListByJobId(Integer jobId) {
		JobTraceExample example = new JobTraceExample();
		example.createCriteria().andJobIdEqualTo(jobId);
		return jobTraceMapper.selectByExample(example);
	}

	@Override
	public int deleteJobTraceByJobId(Integer jobId) {
		JobTraceExample example = new JobTraceExample();
		example.createCriteria().andJobIdEqualTo(jobId);
		return jobTraceMapper.deleteByExample(example);
	}

	@Override
	public int insertJobTrace(JobTrace jobTrace) {
		return jobTraceMapper.insert(jobTrace);
	}

}
