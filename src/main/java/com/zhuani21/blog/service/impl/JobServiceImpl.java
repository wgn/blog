package com.zhuani21.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	public List<JobCustom> queryJobList(String timeRange) throws Exception {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("1".equals(timeRange)){
			now.add(Calendar.DAY_OF_MONTH, 1);
		}else if("2".equals(timeRange)){
			now.add(Calendar.DAY_OF_MONTH, 7);
		}else if("3".equals(timeRange)){
			now.add(Calendar.DAY_OF_MONTH, 30);
		}else {
			timeRange = null;
		}
		if(StringUtils.isNoneBlank(timeRange)){
			timeRange = sdf.format(now.getTime());
		}else{
			timeRange = null;
		}
		
		return jobDao.selectJobList(timeRange);
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
