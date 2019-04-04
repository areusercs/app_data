package com.certus.ivma.entity;

/**
 * APP视频抓取任务
 * 
 * @author 	pengpeng
 * @date   		2017年6月15日 下午1:22:18
 * @version 	1.0
 */
public class AppVideoCrawlTask {

	/** 任务ID */
	private Long taskId;
	
	/** 任务ID,其值以APPID为ID */
	private Long appId;
	
	private String appName;
	
	private String appEngName;
	
	/** 任务类型, #AppVideoCrawlTaskType */
	private String taskType;
	
	/** 任务批次号 */
	private String batchNo;
	
	/** 任务状态 #AppVideoCrawlTaskStatus */
	private Integer status;
	
	/** 任务创建时间 */
	private String createTime;
	
	/** 任务调度时间 */
	private String scheduleTime;
	
	/** 任务分配到哪台机器上 */
	private String scheduleHost;
	
	/** 运行时间 */
	private String runningTime;
	
	private String finishedTime;
	
	private String statusName;
	
	/** 运行时长 */
	private Integer runningTimes;

	/** 任务优先级,数字越小优先级越高,数据来源于IVMA_APP.APP_CLASSIFY字段 */
	private Integer priority;
	
	private String taskTypeName;

	/** 任务调度类型 #TASK_DISPATCH_TYPE */
	private String dispatchType;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
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

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public String getScheduleHost() {
		return scheduleHost;
	}

	public void setScheduleHost(String scheduleHost) {
		this.scheduleHost = scheduleHost;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	public String getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(String finishedTime) {
		this.finishedTime = finishedTime;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getRunningTimes() {
		return runningTimes;
	}

	public void setRunningTimes(Integer runningTimes) {
		this.runningTimes = runningTimes;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public String getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}

	@Override
	public String toString() {
		return "AppVideoCrawlTask{" +
				"taskId=" + taskId +
				", appId=" + appId +
				", appName='" + appName + '\'' +
				", appEngName='" + appEngName + '\'' +
				", taskType='" + taskType + '\'' +
				", batchNo='" + batchNo + '\'' +
				", status=" + status +
				", createTime='" + createTime + '\'' +
				", scheduleTime='" + scheduleTime + '\'' +
				", scheduleHost='" + scheduleHost + '\'' +
				", runningTime='" + runningTime + '\'' +
				", finishedTime='" + finishedTime + '\'' +
				", statusName='" + statusName + '\'' +
				", runningTimes=" + runningTimes +
				", priority=" + priority +
				", taskTypeName='" + taskTypeName + '\'' +
				", dispatchType=" + dispatchType +
				'}';
	}
}
