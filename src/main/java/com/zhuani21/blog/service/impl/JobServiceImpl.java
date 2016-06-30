package com.zhuani21.blog.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.Job;
import com.zhuani21.blog.auto.bean.JobExample;
import com.zhuani21.blog.auto.mapper.JobMapper;
import com.zhuani21.blog.bean.JobCustom;
import com.zhuani21.blog.dao.JobDao;
import com.zhuani21.blog.service.JobService;
@Service("jobService")
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobMapper jobMapper;
	@Autowired
	private JobDao jobDao;

	@Override
	public List<JobCustom> queryJobList() throws Exception {
		return jobDao.selectJobList();
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
		jobDao.insert(job);
	}

	@Override
	public void updateJob(JobCustom job) {
		jobMapper.updateByPrimaryKey(job);
	}

	@Override
	public String findOriginalFileNameByFilePath(String name) {
		JobExample example = new JobExample();
		example.createCriteria().andFilepathEqualTo(name);
		List<Job> jobList = jobMapper.selectByExample(example);
		if(null!=jobList && jobList.size()==1){
			return jobList.get(0).getOldFilename();
		}
		return null;
	}

	@Override
	public int deleteJobById(Integer id) {
		return jobMapper.deleteByPrimaryKey(id);
	}
}
