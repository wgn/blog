package com.zhuani21.blog.service;

import java.util.List;

import com.zhuani21.blog.auto.bean.JobTrace;

public interface JobTraceService {

	List<JobTrace> selectJobTraceListByJobId(Integer jobId);

}
