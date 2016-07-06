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

@Controller
@RequestMapping("/job")
public class JobController {

	private static Logger logger = Logger.getLogger(JobController.class);

	@Autowired
	JobService jobService;
	@Autowired
	JobTraceService jobTraceService;

	@RequestMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<JobCustom> jobList = jobService.queryJobList();
		modelAndView.addObject("jobList", jobList);
		// modelAndView.addObject("fileDir",
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

		jobService.insertJob(job);

		createAndInsertJobTrace(job);

		return "redirect:/job/list";
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
			job.setOldFilename(job.getOriginalOldFile());
			job.setFilepath(job.getOriginalFilePath());
		}
		
		jobService.updateJob(job);
		
		updateJobTrace(job ,oldJob);
		
		attr.addFlashAttribute("msg", "修改成功");
		return "redirect:/job/edit/" + job.getJobId();
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
	 * 暂时作业计划的修改，都采用删除原来计划，从新生成第一个任务的方式。
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
			createAndInsertJobTrace(job);
		}else if(WConstant.JOB_CYCLE_TYPE_REREAD.equals(jobCycleType)){
			int rereadTime = job.getRereadTime();
			int oldRereadTime = oldJob.getRereadTime();
			if(rereadTime!=oldRereadTime){
				jobTraceService.deleteJobTraceByJobId(job.getJobId());
				job.setCreateTime(new Date());
				createAndInsertJobTrace(job);
			}
		}else if(WConstant.JOB_CYCLE_TYPE_REVIEW.equals(jobCycleType)){
			String setting = job.getCycleSetting();
			String oldSetting = oldJob.getCycleSetting();
			if(!setting.equals(oldSetting)){
				jobTraceService.deleteJobTraceByJobId(job.getJobId());
				job.setCreateTime(new Date());
				createAndInsertJobTrace(job);
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

	private int createAndInsertJobTrace(JobCustom job) {
		JobTrace jobTrace = createJobTrace(job);
		return jobTraceService.insertJobTrace(jobTrace);
	}

	private JobTrace createJobTrace(JobCustom job) {
		if (null != job && null != job.getJobId()) {
			JobTrace jobTrace = new JobTrace();
			jobTrace.setJobId(job.getJobId());
			jobTrace.setJobCycleType(job.getJobCycleType());
			jobTrace.setStep("1");
			Float stepValue = calcStepValue(job);
			if(null==stepValue || stepValue<=0){
				throw new BlogBaseException("无法计算作业计划执行的时间");
			}
			jobTrace.setStepValue(stepValue);
			jobTrace.setStatus("now");

			Date planTime = null;
			planTime = calcPlanTime(job);
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
	 * @return
	 */
	private Float calcStepValue(JobCustom job) {
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
				String firstStep = intervalDays[0];
				Float firstStepNo = Float.parseFloat(firstStep);
				return firstStepNo;
			}
		}
		return null;
	}

	/**
	 * 计算第一次作业的开始时间 根据类型不同分别处理
	 * 
	 * @param job
	 *            - 使用对象中的jobCycleType以及不同type对应的值
	 * @return Date - 开始的时间
	 */
	private Date calcPlanTime(JobCustom job) {
		Integer jobId = job.getJobId();
		if (null != jobId) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(job.getCreateTime());
			Float stepValue = calcStepValue(job);
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
