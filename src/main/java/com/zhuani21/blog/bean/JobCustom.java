package com.zhuani21.blog.bean;

import org.apache.commons.lang3.StringUtils;

import com.zhuani21.blog.auto.bean.Job;

public class JobCustom extends Job {

	//这两个字段是为了更新的时候从前台接收原来的文件名和文件实际名称（filepath）的
	private String originalOldFile;
	private String originalFilePath;

	public String getOriginalOldFile() {
		return originalOldFile;
	}

	public void setOriginalOldFile(String originalOldFile) {
		this.originalOldFile = originalOldFile;
	}

	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	@Override
	public void setJobDescription(String jobDescription) {
		if(StringUtils.isBlank(jobDescription)){
			super.setJobDescription(null);
		}else{
			super.setJobDescription(jobDescription);
		}
	}

	@Override
	public void setJobLink(String jobLink) {
		if(StringUtils.isBlank(jobLink)){
			super.setJobLink(null);
		}else{
			super.setJobLink(jobLink);
		}
	}

	
}
