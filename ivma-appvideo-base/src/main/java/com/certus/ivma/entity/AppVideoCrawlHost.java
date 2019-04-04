package com.certus.ivma.entity;

import java.io.Serializable;

/**
 * APP抓取主机信息
 * 
 * @author 	pengpeng
 * @date   		2017年7月19日 上午10:49:25
 * @version 	1.0
 */
public class AppVideoCrawlHost implements Serializable {

	private static final long serialVersionUID = 1L;

	private String hostIp;
	
	private Boolean active;
	
	private String crawlType;
	
	private String insertTime;
	
	private String updateTime;
	
	private String keepAliveTime;

	private Integer maxPollSize;

	private Integer nowPollSize;

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCrawlType() {
		return crawlType;
	}

	public void setCrawlType(String crawlType) {
		this.crawlType = crawlType;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(String keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public Integer getMaxPollSize() {
		return maxPollSize;
	}

	public void setMaxPollSize(Integer maxPollSize) {
		this.maxPollSize = maxPollSize;
	}

	public Integer getNowPollSize() {
		return nowPollSize;
	}

	public void setNowPollSize(Integer nowPollSize) {
		this.nowPollSize = nowPollSize;
	}

	@Override
	public String toString() {
		return "AppVideoCrawlHost{" +
				"hostIp='" + hostIp + '\'' +
				", active=" + active +
				", crawlType='" + crawlType + '\'' +
				", insertTime='" + insertTime + '\'' +
				", updateTime='" + updateTime + '\'' +
				", keepAliveTime='" + keepAliveTime + '\'' +
				", maxPollSize=" + maxPollSize +
				", nowPollSize=" + nowPollSize +
				'}';
	}
}
