package com.certus.ivma.entity;

public class RequestInfo {

	private String appEngName;

	private String storageAddress;

	private String configUri;

	private  AppVideoCrawlTask appVideoCrawlTask;

	public RequestInfo(String appEngName, String storageAddress, String configUri, AppVideoCrawlTask appVideoCrawlTask) {
		this.appEngName = appEngName;
		this.storageAddress = storageAddress;
		this.configUri = configUri;
		this.appVideoCrawlTask = appVideoCrawlTask;
	}
	public RequestInfo(String appEngName, String storageAddress,  AppVideoCrawlTask appVideoCrawlTask) {
		this.appEngName = appEngName;
		this.storageAddress = storageAddress;
		this.appVideoCrawlTask = appVideoCrawlTask;
	}

	public RequestInfo() {
	}


	public String getConfigUri() {
		return configUri;
	}

	public void setConfigUri(String configUri) {
		this.configUri = configUri;
	}

	public String getAppEngName() {
		return appEngName;
	}

	public void setAppEngName(String appEngName) {
		this.appEngName = appEngName;
	}

	public String getStorageAddress() {
		return storageAddress;
	}

	public void setStorageAddress(String storageAddress) {
		this.storageAddress = storageAddress;
	}

	public AppVideoCrawlTask getAppVideoCrawlTask() {
		return appVideoCrawlTask;
	}

	public void setAppVideoCrawlTask(AppVideoCrawlTask appVideoCrawlTask) {
		this.appVideoCrawlTask = appVideoCrawlTask;
	}

	@Override
	public String toString() {
		return "RequestInfo{" +
				"appEngName='" + appEngName + '\'' +
				", storageAddress='" + storageAddress + '\'' +
				", configUri='" + configUri + '\'' +
				", appVideoCrawlTask=" + appVideoCrawlTask +
				'}';
	}
}
