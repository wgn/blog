package com.zhuani21.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.Job;
import com.zhuani21.blog.bean.JobCustom;
import com.zhuani21.blog.service.JobService;
import com.zhuani21.blog.util.BeanCopyUtils;
import com.zhuani21.blog.util.WConstant;

@Controller
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	JobService jobService;

	@RequestMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Job> jobList = jobService.queryJobList();
		List<JobCustom> jobCustomList = BeanCopyUtils.getCustomBeanList(jobList, JobCustom.class);
		modelAndView.addObject("jobList", jobCustomList);
		modelAndView.addObject("fileDir", WConstant.FILE_DIR);
		modelAndView.setViewName("jobList");
		return modelAndView;
	}
	
	@RequestMapping(value={"/add"},method={RequestMethod.GET})
	public ModelAndView addView(Integer id) throws Exception {
		String opType = "add";
		return addAndEditView(id, opType);
	}
	@RequestMapping(value={"/edit/{id}"},method={RequestMethod.GET})
	public ModelAndView editView(@PathVariable Integer id) throws Exception {
		String opType = "edit";
		return addAndEditView(id, opType);
	}

	private ModelAndView addAndEditView(Integer id, String opType) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("opType", opType);
		if(null!=id){
			JobCustom job = jobService.queryJobById(id);
			modelAndView.addObject("job", job);
		}
		modelAndView.setViewName("jobUpdate");
		return modelAndView;
	}
	private ModelAndView addAndEditView(Job job, String opType) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("opType", opType);
		if(null!=job){
			modelAndView.addObject("job", job);
		}
		modelAndView.setViewName("jobUpdate");
		return modelAndView;
	}
	
	@RequestMapping(value={"/add"},method={RequestMethod.POST})
	public String addJob(JobCustom job,MultipartFile jobFile) throws Exception {
		String[] fileNames = saveFile(jobFile,WConstant.FILE_DIR); 
		if(null!=fileNames && fileNames.length==2){
			job.setOldFilename(fileNames[0]);
		    job.setFilepath(fileNames[1]);
		}
	    job.setJobId(null);
	    jobService.insertJob(job);
		return "redirect:/job/add/";
	}

	private String[] saveFile(MultipartFile jobFile, String newFileDir) throws IOException {
		String[] names = null;
		if(StringUtils.isBlank(newFileDir) || jobFile==null){
			return null;
		}
		File dir = new File(newFileDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		String newFileName = null;
		 //原始名称
	    String originalFilename = jobFile.getOriginalFilename();
	    //上传图片
	    if(StringUtils.isNotBlank(originalFilename)){
	    	names = new String[2];
	    	names[0] = originalFilename;
	        //存储图片的物理路径
	        String pic_path = newFileDir;
	        //新的图片名称
	        newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
	        //新图片
	        File newFile = new File(pic_path+newFileName);
	        //将内存中的数据写入磁盘
	        jobFile.transferTo(newFile);
	        names[1] = newFileName;
	    }
		return names;
	}
	@RequestMapping(value={"edit"},method={RequestMethod.POST})
	public ModelAndView editJob(JobCustom job,MultipartFile jobFile) throws Exception {
		String[] fileNames = saveFile(jobFile,WConstant.FILE_DIR); 
		if(null!=fileNames && fileNames.length==2){
			job.setOldFilename(fileNames[0]);
		    job.setFilepath(fileNames[1]);
		}
	    job.setJobId(null);
	    jobService.insertJob(job);
		return null;
	}
	
}
