package com.zhuani21.blog.vo;

import java.util.Date;

public class JobProgressVo {
	private Integer jobId;
	private Integer jobTraceId;
	private Date finishTime;
	private String comment;
	private String progressStatus;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getJobTraceId() {
		return jobTraceId;
	}

	public void setJobTraceId(Integer jobTraceId) {
		this.jobTraceId = jobTraceId;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	@Override
	public String toString() {
		return "JobProgressVo [jobId=" + jobId + ", jobTraceId=" + jobTraceId + ", finishTime=" + finishTime + ", comment=" + comment + ", progressStatus=" + progressStatus + "]";
	}
	
}
