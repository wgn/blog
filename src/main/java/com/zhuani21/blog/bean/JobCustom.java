package com.zhuani21.blog.bean;

import com.zhuani21.blog.auto.bean.Job;

public class JobCustom extends Job {

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

}
