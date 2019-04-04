package com.certus.ivma.entity;

/**
 * APP视频抓取任务日志
 * 
 * @author 	pengpeng
 * @date   		2017年6月15日 下午1:35:03
 * @version 	1.0
 */
public class AppVideoCrawlLog {

	/** 日志ID */
	private Long id;
	
	/** 任务ID */
	private Long taskId;
	
	/** 任务开始执行时间 */
	private String taskStartTime;
	
	/** 任务结束执行时间 */
	private String taskEndTime;
	
	/** 任务执行的服务器IP */
	private String serverIp;

	/** 任务执行成功与否 */
	private Boolean success;
	
	/** 任务执行日志消息 */
	private String message;
	
	private String insertTime;
	
	private String appName;

	private String appEngName;
	
	/** 运行时长 */
	private Integer runningTimes;
	
	private Long appId;
	
	private String taskBatchNo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(String taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public String getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppEngName() {
		return appEngName;
	}

	public void setAppEngName(String appEngName) {
		this.appEngName = appEngName;
	}

	public Integer getRunningTimes() {
		return runningTimes;
	}

	public void setRunningTimes(Integer runningTimes) {
		this.runningTimes = runningTimes;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getTaskBatchNo() {
		return taskBatchNo;
	}

	public void setTaskBatchNo(String taskBatchNo) {
		this.taskBatchNo = taskBatchNo;
	}

	public String toString() {
		return "AppVideoCrawlLog [id=" + id + ", taskId=" + taskId + ", taskStartTime="
				+ taskStartTime + ", taskEndTime=" + taskEndTime + ", serverIp=" + serverIp + ", success=" + success
				+ ", message=" + message + ", insertTime=" + insertTime + ", appName=" + appName + "]";
	}
	
}
