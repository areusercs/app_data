package com.certus.ivma.entity;

public class ReturnInfo {

	private boolean isSuccess;
	
	private String info;
	
	private String ip;
	
	private String port;

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "ReturnInfo{" +
				"isSuccess='" + isSuccess + '\'' +
				", info='" + info + '\'' +
				", ip='" + ip + '\'' +
				", port='" + port + '\'' +
				'}';
	}
}
