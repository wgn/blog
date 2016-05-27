package com.zhuani21.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.Job;
import com.zhuani21.blog.auto.bean.JobTrace;
import com.zhuani21.blog.bean.JobCustom;
import com.zhuani21.blog.bean.PublicVO;
import com.zhuani21.blog.data.SysProperties;
import com.zhuani21.blog.exception.BlogBaseException;
import com.zhuani21.blog.service.JobService;
import com.zhuani21.blog.service.JobTraceService;
import com.zhuani21.blog.util.BeanCopyUtils;

@Controller
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	JobService jobService;
	@Autowired
	JobTraceService jobTraceService;

	@RequestMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Job> jobList = jobService.queryJobList();
		List<JobCustom> jobCustomList = BeanCopyUtils.getCustomBeanList(jobList, JobCustom.class); 
		modelAndView.addObject("jobList", jobCustomList);
		//modelAndView.addObject("fileDir", SysProperties.get("reviewFileUploadFilePath"));
		modelAndView.setViewName("jobList");
		return modelAndView;
	}
	@RequestMapping("/t/list")
	public ModelAndView jobTraceList() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
	@RequestMapping(value={"/add/{id}"},method={RequestMethod.GET})
	public ModelAndView addView(@PathVariable Integer id) throws Exception {
		String opType = "add";
		return addAndEditView(id, opType);
	}
	@RequestMapping(value={"/add"},method={RequestMethod.GET})
	public ModelAndView addView() throws Exception {
		String opType = "add";
		return addAndEditView(null, opType);
	}
	@RequestMapping(value={"/edit/{id}"},method={RequestMethod.GET})
	public ModelAndView editView(@PathVariable Integer id) throws Exception {
		String opType = "edit";
		return addAndEditView(id, opType);
	}
	
	@RequestMapping(value={"/delete/{id}"},method={RequestMethod.POST})
	public @ResponseBody PublicVO delete(@PathVariable Integer id){
		if(null!=id){
			PublicVO vo = new PublicVO();
			int i = jobService.deleteJobById(id);
			if(i>0){
				vo.setResult(true);
			}
			return vo;
		}
		return null;
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
	
	@RequestMapping(value={"/add"},method={RequestMethod.POST})
	public String addJob(JobCustom job,MultipartFile jobFile) throws Exception {
		String[] fileNames = saveFile(jobFile,SysProperties.get("reviewFileUploadFilePath")); 
		if(null!=fileNames && fileNames.length==2){
			job.setOldFilename(fileNames[0]);
		    job.setFilepath(fileNames[1]);
		}
	    job.setJobId(null);
	    
	    jobService.insertJob(job);
	    
	    JobTrace jobTrace = createJobTrace(job);
	    if(null!=jobTrace){
	    	//jobTraceService.insertJobTrace(jobTrace);
	    }
		return "redirect:/job/list";
	}

	private JobTrace createJobTrace(JobCustom job) {
		if(null!=job && null!=job.getJobId()){
			JobTrace jobTrace = new JobTrace();
			jobTrace.setJobId(job.getJobId());
			jobTrace.setJobCycleType(job.getJobCycleType());
			
			Date planTime = null;
			planTime = calcPlanTime(job);
			if(null==planTime){
				throw new BlogBaseException("无法计算作业开始的时间");
			}
			jobTrace.setPlanTime(planTime);
			
			jobTrace.setStatus("now");
			jobTrace.setFinishTime(null);
			
			jobTrace.setStep("1");
			return jobTrace;
		}
		return null;
	}
	/**
	 * 计算第一次作业的开始时间
	 * 根据类型不同分别处理
	 * 
	 * @param job - 使用对象中的jobCycleType以及不同type对应的值
	 * @return Date - 开始的时间
	 */
	private Date calcPlanTime(JobCustom job) {
		Integer jobId = job.getJobId();
		if(null!=jobId){
			List<JobTrace> jobTraceList = jobTraceService.selectJobTraceListByJobId(jobId);
			if(null!=jobTraceList && jobTraceList.size()>0){
				for(JobTrace jobTrace : jobTraceList){
					if("now".equals(jobTrace.getStatus())){
						
					}
				}
			} else{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(job.getCreateTime());
				if("review".equals(job.getJobCycleType())){
					String cycleString = job.getCycleSetting();
					if(StringUtils.isBlank(cycleString)){
						return null;
					}
					String [] intervalDays = cycleString.split(",");
					if(intervalDays!=null && intervalDays.length>0){
						String firstStep  = intervalDays[0];
						Float firstStepNo = Float.parseFloat(firstStep);
						int hours = (int) (firstStepNo * 24);
						
						calendar.add(Calendar.HOUR, hours);
						return calendar.getTime();
					}
				}else if("reread".equals(job.getJobCycleType())){
					Integer days = job.getRereadTime();
					if(null==days || days==0){
						return null;
					}
					
					calendar.add(Calendar.DAY_OF_MONTH, days);
					return calendar.getTime();
				}
			}
		}
		
		return null;
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
	public String editJob(JobCustom job,MultipartFile jobFile) throws Exception {
		String[] fileNames = saveFile(jobFile,SysProperties.get("reviewFileUploadFilePath")); 
		if(null!=fileNames && fileNames.length==2){
			job.setOldFilename(fileNames[0]);
		    job.setFilepath(fileNames[1]);
		    deleteFile(job.getOriginalFilePath(),SysProperties.get("reviewFileUploadFilePath"));
		}else{
			//这里要处理更新时候文件的问题。
			job.setOldFilename(job.getOriginalOldFile());
		    job.setFilepath(job.getOriginalFilePath());
		}
		
	    jobService.updateJob(job);
	    return "redirect:/job/edit/"+job.getJobId();
	}

	private void deleteFile(String originalFilePath, String filePath) {
		if(StringUtils.isNoneBlank(originalFilePath) && StringUtils.isNoneBlank(filePath) ){
			File deleteFile = new File(filePath + originalFilePath);
			if(deleteFile.exists()){
				deleteFile.delete();
			}
		}
	}

	@RequestMapping("/download/{filename}")  
	public ResponseEntity<byte[]> downloadReviewFile(@PathVariable String filename) throws IOException {  
		String path = SysProperties.get("reviewFileUploadFilePath");
	    
	    File dir = new File(path);
	    if(dir.isDirectory()){
	    	for(File f:dir.listFiles()){
	    		if(f.getName().startsWith(filename)){
	    			HttpHeaders headers = new HttpHeaders();  
	    		    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    			String originalFileName = jobService.findOriginalFileNameByFilePath(f.getName());
	    			if(null==originalFileName){
	    				originalFileName = f.getName();
	    			}
	    			
	    		    headers.setContentDispositionFormData("attachment", java.net.URLEncoder.encode(originalFileName, "UTF-8"));
	    			
	    			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f),  
	    	    			headers, HttpStatus.CREATED);  
	    		}
	    	}
	    }
	    return null;
	} 
}
