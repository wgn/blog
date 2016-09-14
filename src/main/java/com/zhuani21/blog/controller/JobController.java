package com.zhuani21.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhuani21.blog.auto.bean.JobTrace;
import com.zhuani21.blog.bean.JobCustom;
import com.zhuani21.blog.bean.PublicVO;
import com.zhuani21.blog.data.SysProperties;
import com.zhuani21.blog.exception.BlogBaseException;
import com.zhuani21.blog.service.JobService;
import com.zhuani21.blog.service.JobTraceService;
import com.zhuani21.blog.util.WConstant;
import com.zhuani21.blog.vo.JobProgressVo;

@Controller
@RequestMapping("/job")
public class JobController {

	private static Logger logger = Logger.getLogger(JobController.class);

	@Autowired
	JobService jobService;
	@Autowired
	JobTraceService jobTraceService;

	@RequestMapping("/list")
	public ModelAndView list(String timeRange) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<JobCustom> jobList = jobService.queryJobList(timeRange);
		modelAndView.addObject("jobList", jobList);
		modelAndView.addObject("timeRange",timeRange);
		// SysProperties.get("reviewFileUploadFilePath"));
		modelAndView.setViewName("jobList");
		return modelAndView;
	}

	@RequestMapping("/t/list")
	public ModelAndView jobTraceList() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}

	@RequestMapping(value = { "/add/{id}" }, method = { RequestMethod.GET })
	public ModelAndView addView(@PathVariable Integer id) throws Exception {
		String opType = "add";
		return addAndEditView(id, opType);
	}

	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public ModelAndView addView() throws Exception {
		String opType = "add";
		return addAndEditView(null, opType);
	}

	@RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
	public String addJob(JobCustom job, MultipartFile jobFile) throws Exception {
		String[] fileNames = saveFile(jobFile, SysProperties.get("reviewFileUploadFilePath"));
		if (null != fileNames && fileNames.length == 2) {
			job.setOldFilename(fileNames[0]);
			job.setFilepath(fileNames[1]);
		}
		job.setJobId(null);
		job.setCreateTime(new Date());

		checkSycleSetting(job.getCycleSetting());
		
		initJobStatus(job);
		
		jobService.insertJob(job);

		createAndInsertJobTrace(job,job.getCreateTime());

		return "redirect:/job/list";
	}

	private void initJobStatus(JobCustom job) {
		String jobCycleType = job.getJobCycleType();
		if(WConstant.JOB_CYCLE_TYPE_REREAD.equals(jobCycleType)){
			job.setJobStatus("1/1");
		}else if (WConstant.JOB_CYCLE_TYPE_REVIEW.equals(jobCycleType)){
			int n = job.getCycleSetting().split(",").length;
			job.setJobStatus("1/" + n);
		}
	}

	@RequestMapping(value = { "/edit/{id}" }, method = { RequestMethod.GET })
	public ModelAndView editView(@PathVariable Integer id) throws Exception {
		String opType = "edit";
		return addAndEditView(id, opType);
	}

	@RequestMapping(value = { "edit" }, method = { RequestMethod.POST })
	public String editJob(JobCustom job, MultipartFile jobFile,RedirectAttributes attr) throws Exception {
		JobCustom oldJob = jobService.queryJobById(job.getJobId());
		
		String[] fileNames = saveFile(jobFile, SysProperties.get("reviewFileUploadFilePath"));
		if (null != fileNames && fileNames.length == 2) {
			//如果修改了文件，从新设置文件，并删除原来的文件
			//原来的文件使用originalFilePath,originalOldFile保存
			job.setOldFilename(fileNames[0]);
			job.setFilepath(fileNames[1]);
			deleteFile(job.getOriginalFilePath(), SysProperties.get("reviewFileUploadFilePath"));
		} else {
			// 这里要处理更新时候文件的问题。
			job.setOldFilename(StringUtils.isBlank(job.getOriginalOldFile())?null:job.getOriginalOldFile());
			job.setFilepath(StringUtils.isBlank(job.getOriginalFilePath())?null:job.getOriginalFilePath());
		}
		
		checkSycleSetting(job.getCycleSetting());
		
		updateJobStatus(job,oldJob);
		
		jobService.updateJob(job);
		
		updateJobTrace(job ,oldJob);
		
		attr.addFlashAttribute("msg", "修改成功");
		return "redirect:/job/edit/" + job.getJobId();
	}

	private void checkSycleSetting(String cycleSetting) {
		try{
			for(String s : cycleSetting.split(",")){
				Integer.parseInt(s);
			}
		}catch(Exception e){
			throw new BlogBaseException("周期设置有误，只能是数字，并且以半角英文逗号分割。");
		}
		
	}

	private void updateJobStatus(JobCustom job, JobCustom oldJob) {
		String jobCycleType = job.getJobCycleType();
		String oldJobCycleType = oldJob.getJobCycleType();
		if(!jobCycleType.equals(oldJobCycleType)){
			initJobStatus(job);
		}else if(WConstant.JOB_CYCLE_TYPE_REREAD.equals(jobCycleType)){
			int rereadTime = job.getRereadTime();
			int oldRereadTime = oldJob.getRereadTime();
			if(rereadTime!=oldRereadTime){
				initJobStatus(job);
			}
		}else if(WConstant.JOB_CYCLE_TYPE_REVIEW.equals(jobCycleType)){
			String setting = job.getCycleSetting();
			String oldSetting = oldJob.getCycleSetting();
			if(!setting.equals(oldSetting)){
				initJobStatus(job);
			}
		}
	}

	@RequestMapping(value = { "/progress/{id}" }, method = { RequestMethod.GET })
	public ModelAndView jobProgressView(@PathVariable Integer id) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != id) {
			JobCustom job = jobService.queryJobById(id);
			JobTrace jobTrace = jobTraceService.queryNowJobTraceByJobId(id);
			modelAndView.addObject("job", job);
			modelAndView.addObject("jobTrace", jobTrace);
		}
		modelAndView.setViewName("jobProgress");
		return modelAndView;
	}
	
	@RequestMapping(value = { "progress" }, method = { RequestMethod.POST })
	public String jobProgress(JobProgressVo jobProgressVo,RedirectAttributes attr) throws Exception {
		Integer jobId = jobProgressVo.getJobId();
		Integer jobTraceId = jobProgressVo.getJobTraceId();
		String progressStatus = jobProgressVo.getProgressStatus();
		String comment = jobProgressVo.getComment();
		Date finishTime = jobProgressVo.getFinishTime();
		if(finishTime==null){
			finishTime=new Date();
		}
		
		JobCustom jobCustom = jobService.queryJobById(jobId);
		if("finish".equals(jobCustom.getJobStatus())){
			attr.addFlashAttribute("msg", "作业已完成，去做下一个作业吧。");
			return "redirect:/job/progress/" + jobProgressVo.getJobId();
		}
		JobTrace jobTrace = jobTraceService.queryById(jobTraceId);
		Date planTime = jobTrace.getPlanTime();
		if(planTime.after(finishTime)){
			attr.addFlashAttribute("msg", "计划复习的时间还没有到。");
			return "redirect:/job/progress/" + jobProgressVo.getJobId();
		}
		
		if("complete".equals(progressStatus)){
			//更新job
			calcAndSetJobStatus(jobCustom,"complete");
			jobService.updateJob(jobCustom);
			//更新jobTrace
			//创建并保存下一个的jobTrace
			comment = StringUtils.isBlank(comment)?"完成本次":comment;
			jobTrace.setComment(comment);
			jobTrace.setFinishTime(finishTime);
			jobTrace.setStatus("pass");
			jobTraceService.update(jobTrace);
			
			createAndInsertJobTrace(jobCustom, finishTime);
			
		}else if ("redo".equals(progressStatus)){
			//更新job，从做的话，作业本身进度不变，多了一条jobTrace而已
			//更新jobTrace
			comment = StringUtils.isBlank(comment)?"重复本次":comment;
			jobTrace.setComment(comment);
			jobTrace.setFinishTime(finishTime);
			jobTrace.setStatus("redo");
			jobTraceService.update(jobTrace);
			//创建并保存下一个的jobTrace
			createAndInsertJobTrace(jobCustom, finishTime);
		}else if ("restart".equals(progressStatus)){
			//更新job，重新开始的话，将进度更新为1/1或者1/n
			initJobStatus(jobCustom);
			jobService.updateJob(jobCustom);
			//更新jobTrace
			comment = StringUtils.isBlank(comment)?"重新开始":comment;
			jobTrace.setComment(comment);
			jobTrace.setFinishTime(finishTime);
			jobTrace.setStatus("restart");
			jobTraceService.update(jobTrace);
			//创建并保存下一个的jobTrace
			createAndInsertJobTrace(jobCustom, finishTime);
		}else {
			throw new BlogBaseException("progressStatus的值不正确，它的值只能是[complete,redo,restart]其中之一");
		}
		
		attr.addFlashAttribute("msg", "操作成功");
		return "redirect:/job/progress/" + jobProgressVo.getJobId();
	}

	private JobCustom calcAndSetJobStatus(JobCustom jobCustom, String progressFlag) {
		String jobStatus = jobCustom.getJobStatus();
		if("finish".equals(jobStatus)){
			return jobCustom;
		}
		String currentStep = jobStatus.split("/")[0];
		String totalStep = jobStatus.split("/")[1];
		if("complete".equals(progressFlag)){
			if(currentStep.equals(totalStep)){
				jobCustom.setJobStatus("finish");
			}else{
				currentStep = (Integer.parseInt(currentStep)+1) + "";
				jobCustom.setJobStatus(currentStep + "/" + totalStep); 
			}
		}
		return jobCustom;
	}

	@RequestMapping(value = { "/delete/{id}" }, method = { RequestMethod.POST })
	public @ResponseBody PublicVO delete(@PathVariable Integer id) {
		if (null != id) {
			int jobUpdateCount = 0;
			int jobTraceUpdateCount = 0;
			PublicVO vo = new PublicVO();
			JobCustom job = jobService.queryJobById(id);
			if(null!=job){
				jobUpdateCount = jobService.deleteJobById(id);
				if (jobUpdateCount > 0) {
					deleteFile(job.getFilepath(), SysProperties.get("reviewFileUploadFilePath"));
					jobTraceUpdateCount = deleteJobTraceByJobId(id);
					vo.setResult(true);
				}
			}
			logger.info("delete job id=" + id + ",delete job count=" + jobUpdateCount + ",jobTrace count=" + jobTraceUpdateCount);
			return vo;
		}
		return null;
	}

	private int deleteJobTraceByJobId(Integer id) {
		return jobTraceService.deleteJobTraceByJobId(id);
	}
	/**
	 * 暂时处理方案：作业计划的修改，都采用删除原来计划，从新生成第一个任务的方式。
	 * 从新设置了createTime这样任务的计算时间从心开始，但是数据库并没有改变。
	 * 修改的逻辑其实是很奇怪的，以后考虑禁止修改计划，如果要调整计划，更好的方式是使用该任务作为模版新建任务，并且删除原来的任务。
	 * @param job
	 * @param oldJob
	 */
	private void updateJobTrace(JobCustom job, JobCustom oldJob) {
		String jobCycleType = job.getJobCycleType();
		String oldJobCycleType = oldJob.getJobCycleType();
		if(!jobCycleType.equals(oldJobCycleType)){
			jobTraceService.deleteJobTraceByJobId(job.getJobId());
			job.setCreateTime(new Date());
			createAndInsertJobTrace(job,job.getCreateTime());
		}else if(WConstant.JOB_CYCLE_TYPE_REREAD.equals(jobCycleType)){
			int rereadTime = job.getRereadTime();
			int oldRereadTime = oldJob.getRereadTime();
			if(rereadTime!=oldRereadTime){
				jobTraceService.deleteJobTraceByJobId(job.getJobId());
				job.setCreateTime(new Date());
				createAndInsertJobTrace(job,job.getCreateTime());
			}
		}else if(WConstant.JOB_CYCLE_TYPE_REVIEW.equals(jobCycleType)){
			String setting = job.getCycleSetting();
			String oldSetting = oldJob.getCycleSetting();
			if(!setting.equals(oldSetting)){
				jobTraceService.deleteJobTraceByJobId(job.getJobId());
				job.setCreateTime(new Date());
				createAndInsertJobTrace(job,job.getCreateTime());
			}
		}
	}

	private void deleteFile(String originalFilePath, String filePath) {
		if (StringUtils.isNoneBlank(originalFilePath) && StringUtils.isNoneBlank(filePath)) {
			File deleteFile = new File(filePath + originalFilePath);
			if (deleteFile.exists()) {
				deleteFile.delete();
			}
		}
	}

	@RequestMapping("/download/{filename}")
	public ResponseEntity<byte[]> downloadReviewFile(@PathVariable String filename) throws IOException {
		String path = SysProperties.get("reviewFileUploadFilePath");

		File dir = new File(path);
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				if (f.getName().startsWith(filename)) {
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					String originalFileName = jobService.findOriginalFileNameByFilePath(f.getName());
					if (null == originalFileName) {
						originalFileName = f.getName();
					}

					headers.setContentDispositionFormData("attachment", java.net.URLEncoder.encode(originalFileName, "UTF-8"));

					return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f), headers, HttpStatus.CREATED);
				}
			}
		}
		return null;
	}
	/**
	 * 创建并保存jobTrace
	 * @param job 根据job的信息生成大部分jobTrace的信息
	 * @param startTime 计算jobTrace的planTime时，需要：开始时间+间隔时长 = 计划时间
	 * @return
	 */
	private int createAndInsertJobTrace(JobCustom job,Date startTime) {
		if("finish".equals(job.getJobStatus())){
			return -1;
		}
		JobTrace jobTrace = createJobTrace(job,startTime);
		return jobTraceService.insertJobTrace(jobTrace);
	}

	private JobTrace createJobTrace(JobCustom job,Date startTime) {
		if (null != job && null != job.getJobId()) {
			JobTrace jobTrace = new JobTrace();
			jobTrace.setJobId(job.getJobId());
			jobTrace.setJobCycleType(job.getJobCycleType());
			String jobStatus = job.getJobStatus();
			if("finish".equals(jobStatus)){
				return null;
			}
			String jobNowStep =  jobStatus.split("/")[0];
			jobTrace.setStep(jobNowStep);
			Float stepValue = calcStepValue(job,Integer.parseInt(jobNowStep));
			if(null==stepValue || stepValue<=0){
				throw new BlogBaseException("无法计算作业计划执行的时间");
			}
			jobTrace.setStepValue(stepValue);
			jobTrace.setStatus("now");

			Date planTime = null;
			if(1==Integer.parseInt(jobNowStep) && null==startTime){
				startTime = job.getCreateTime();
			}
			planTime = calcPlanTime(job,startTime);
			if (null == planTime) {
				throw new BlogBaseException("无法计算作业开始的时间");
			}
			jobTrace.setPlanTime(planTime);

			jobTrace.setFinishTime(null);
			jobTrace.setComment(null);

			return jobTrace;
		}
		return null;
	}
	/**
	 * 计算job的当前进度的跨度（天）。
	 * 根据这个值可以设置提醒状态。
	 * 比如当前跨度为10天，那么过期1天完成也算比较正常，但是过期10天或者100天，是否可以考虑重新开始任务了。
	 * @param job
	 * @param step 进度,第一次为1，必须大于0
	 * @return
	 */
	private Float calcStepValue(JobCustom job,Integer step) {
		String jobCycleType = job.getJobCycleType();
		if("reread".equals(jobCycleType)){
			Integer days = job.getRereadTime();
			if (null == days || days == 0) {
				return null;
			}
			return days + 0.0f;
		}else if("review".equals(jobCycleType)){
			String cycleString = job.getCycleSetting();
			if (StringUtils.isBlank(cycleString)) {
				return null;
			}
			String[] intervalDays = cycleString.split(",");
			if (intervalDays != null && intervalDays.length > 0) {
				if(step>intervalDays.length){
					throw new BlogBaseException("step值超过复习周期数组长度");
				}
				String firstStep = intervalDays[step-1];
				Float firstStepNo = Float.parseFloat(firstStep);
				return firstStepNo;
			}
		}
		return null;
	}

	/**
	 * 计算第一次作业的计划开始时间 根据类型不同分别处理。
	 * 返回结果 = 计算间隔时间 + 开始时间。
	 * 比如间隔3天，开始时间是2016-09-08 14:45，那么得到的结果就是2016-09-11 14:45
	 * 
	 * @param job
	 *            - 使用对象中的jobCycleType以及不同type对应的值
	 * @param startDate
	 *            - 开始的时间
	 * @return Date - 计划时间
	 */
	private Date calcPlanTime(JobCustom job,Date startDate) {
		Integer jobId = job.getJobId();
		if (null != jobId) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			String jobStatus = job.getJobStatus();
			String jobNowStep =  jobStatus.split("/")[0];
			Float stepValue = calcStepValue(job,Integer.parseInt(jobNowStep));
			int hours = (int) (stepValue * 24);
			calendar.add(Calendar.HOUR, hours);
			return calendar.getTime();
		}
		return null;
	}

	private String[] saveFile(MultipartFile jobFile, String newFileDir) throws IOException {
		String[] names = null;
		if (StringUtils.isBlank(newFileDir) || jobFile == null) {
			return null;
		}
		File dir = new File(newFileDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String newFileName = null;
		// 原始名称
		String originalFilename = jobFile.getOriginalFilename();
		// 上传图片
		if (StringUtils.isNotBlank(originalFilename)) {
			names = new String[2];
			names[0] = originalFilename;
			// 存储图片的物理路径
			String pic_path = newFileDir;
			// 新的图片名称
			newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 新图片
			File newFile = new File(pic_path + newFileName);
			// 将内存中的数据写入磁盘
			jobFile.transferTo(newFile);
			names[1] = newFileName;
		}
		return names;
	}

	private ModelAndView addAndEditView(Integer id, String opType) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("opType", opType);
		if (null != id) {
			JobCustom job = jobService.queryJobById(id);
			modelAndView.addObject("job", job);
		}
		modelAndView.setViewName("jobUpdate");
		return modelAndView;
	}
}
