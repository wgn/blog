package com.zhuani21.blog.auto.bean;

import java.util.Date;

public class Job {
    private Integer jobId;

    private String jobName;

    private String jobCycleType;

    private String jobDescription;

    private String jobLink;

    private String jobStatus;

    private String oldFilename;

    private String filepath;

    private String cycleSetting;

    private Integer rereadTime;

    private Date createTime;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobCycleType() {
        return jobCycleType;
    }

    public void setJobCycleType(String jobCycleType) {
        this.jobCycleType = jobCycleType == null ? null : jobCycleType.trim();
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription == null ? null : jobDescription.trim();
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink == null ? null : jobLink.trim();
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    public String getOldFilename() {
        return oldFilename;
    }

    public void setOldFilename(String oldFilename) {
        this.oldFilename = oldFilename == null ? null : oldFilename.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public String getCycleSetting() {
        return cycleSetting;
    }

    public void setCycleSetting(String cycleSetting) {
        this.cycleSetting = cycleSetting == null ? null : cycleSetting.trim();
    }

    public Integer getRereadTime() {
        return rereadTime;
    }

    public void setRereadTime(Integer rereadTime) {
        this.rereadTime = rereadTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}