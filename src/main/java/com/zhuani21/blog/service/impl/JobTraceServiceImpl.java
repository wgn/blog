package com.zhuani21.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.JobTrace;
import com.zhuani21.blog.auto.bean.JobTraceExample;
import com.zhuani21.blog.auto.mapper.JobTraceMapper;
import com.zhuani21.blog.exception.BlogBaseException;
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

	@Override
	public JobTrace queryNowJobTraceByJobId(Integer jobId) {
		JobTraceExample example = new JobTraceExample();
		example.createCriteria().andJobIdEqualTo(jobId).andStatusEqualTo("now");
		List<JobTrace> jobTraceList = jobTraceMapper.selectByExample(example);
		if(1==jobTraceList.size()){
			return jobTraceList.get(0);
		}else if(0==jobTraceList.size()){
			return null;
		}else if(jobTraceList.size()>1){
			throw new BlogBaseException("查询jobTrace状态位now的结果超过1条，jobId=" + jobId);
		}
		return null;
	}

	@Override
	public JobTrace queryById(Integer id) {
		
		return jobTraceMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(JobTrace jobTrace) {
		jobTraceMapper.updateByPrimaryKey(jobTrace);
	}

}
