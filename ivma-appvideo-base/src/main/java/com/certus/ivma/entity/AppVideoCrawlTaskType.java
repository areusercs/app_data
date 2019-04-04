package com.certus.ivma.entity;

import java.io.Serializable;

public class AppVideoCrawlTaskType implements Serializable {

	private Integer id;

	private String appCrawlTypeCode;

	private String appCrawlTypeName;

	private String crawlTaskCron;

	private char is_expired;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppCrawlTypeCode() {
		return appCrawlTypeCode;
	}

	public void setAppCrawlTypeCode(String appCrawlTypeCode) {
		this.appCrawlTypeCode = appCrawlTypeCode;
	}

	public String getAppCrawlTypeName() {
		return appCrawlTypeName;
	}

	public void setAppCrawlTypeName(String appCrawlTypeName) {
		this.appCrawlTypeName = appCrawlTypeName;
	}

	public String getCrawlTaskCron() {
		return crawlTaskCron;
	}

	public void setCrawlTaskCron(String crawlTaskCron) {
		this.crawlTaskCron = crawlTaskCron;
	}

	public char getIs_expired() {
		return is_expired;
	}

	public void setIs_expired(char is_expired) {
		this.is_expired = is_expired;
	}

	@Override
	public String toString() {
		return "AppVideoCrawlTaskType{" +
				"id=" + id +
				", appCrawlTypeCode='" + appCrawlTypeCode + '\'' +
				", appCrawlTypeName='" + appCrawlTypeName + '\'' +
				", crawlTaskCron='" + crawlTaskCron + '\'' +
				", is_expired=" + is_expired +
				'}';
	}
}
