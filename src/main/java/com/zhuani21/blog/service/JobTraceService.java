package com.zhuani21.blog.service;

import java.util.List;

import com.zhuani21.blog.auto.bean.JobTrace;

public interface JobTraceService {

	List<JobTrace> selectJobTraceListByJobId(Integer jobId);

	int deleteJobTraceByJobId(Integer id);

	int insertJobTrace(JobTrace jobTrace);

	JobTrace queryNowJobTraceByJobId(Integer jobId);
	
	JobTrace queryById(Integer id);

	void update(JobTrace jobTrace);
}
