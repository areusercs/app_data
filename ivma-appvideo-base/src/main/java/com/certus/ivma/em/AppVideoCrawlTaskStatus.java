package com.certus.ivma.em;

/**
 * APP视频抓取任务状态
 * 
 * @author	  	pengpeng
 * @date	  	2014年10月28日 下午5:26:46
 * @version  	1.0
 */
public enum AppVideoCrawlTaskStatus {
	
	TASK_WAIT_SCHEDULING(0, "待分配"), TASK_SCHEDULED(1, "已分配待运行"), TASK_RUNNING(2, "运行中"), TASK_FINISHED(3, "已完成");
	
	private Integer statusCode;
	
	private String statusName;
	

	private AppVideoCrawlTaskStatus(Integer statusCode, String statusName) {
		this.statusCode = statusCode;
		this.statusName = statusName;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public static AppVideoCrawlTaskStatus getStatus(Integer statusCode) {
		for(AppVideoCrawlTaskStatus em : values()){
			if(em.getStatusCode().equals(statusCode)){
				return em;
			}
		}
		return null;
	}
	
}
