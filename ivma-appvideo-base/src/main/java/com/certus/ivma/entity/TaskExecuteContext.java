package com.certus.ivma.entity;

public class TaskExecuteContext {

	/** 任务执行最大 */
	private long maxTaskExecuteTimes;

	/** 脚本路径uri */
	private String groovyScriptHttpPath;

	/** 执行的抓取任务 */
	private AppVideoCrawlTask crawlTask;
	
	/** 执行抓取任务的日志 */
	private AppVideoCrawlLog crawlLog;
	
	public TaskExecuteContext() {
		super();
	}

	public TaskExecuteContext(long maxTaskExecuteTimes, AppVideoCrawlTask crawlTask, AppVideoCrawlLog crawlLog) {
		super();
		this.maxTaskExecuteTimes = maxTaskExecuteTimes;
		this.crawlTask = crawlTask;
		this.crawlLog = crawlLog;
	}

	public TaskExecuteContext(AppVideoCrawlTask crawlTask,long maxTaskExecuteTimes,String groovyScriptHttpPath) {
		super();
		this.crawlTask = crawlTask;
		this.maxTaskExecuteTimes = maxTaskExecuteTimes;
		this.groovyScriptHttpPath = groovyScriptHttpPath;
	}

	public TaskExecuteContext(AppVideoCrawlTask crawlTask, AppVideoCrawlLog crawlLog) {
		super();
		this.crawlTask = crawlTask;
		this.crawlLog = crawlLog;
	}

	public long getMaxTaskExecuteTimes() {
		return maxTaskExecuteTimes;
	}

	public void setMaxTaskExecuteTimes(long maxTaskExecuteTimes) {
		this.maxTaskExecuteTimes = maxTaskExecuteTimes;
	}

	public AppVideoCrawlTask getCrawlTask() {
		return crawlTask;
	}

	public void setCrawlTask(AppVideoCrawlTask crawlTask) {
		this.crawlTask = crawlTask;
	}

	public AppVideoCrawlLog getCrawlLog() {
		return crawlLog;
	}

	public void setCrawlLog(AppVideoCrawlLog crawlLog) {
		this.crawlLog = crawlLog;
	}

	public String getGroovyScriptHttpPath() {
		return groovyScriptHttpPath;
	}

	public void setGroovyScriptHttpPath(String groovyScriptHttpPath) {
		this.groovyScriptHttpPath = groovyScriptHttpPath;
	}
}
