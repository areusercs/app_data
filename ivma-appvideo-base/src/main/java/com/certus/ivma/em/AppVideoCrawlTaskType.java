package com.certus.ivma.em;

/**
 * 抓取任务类型
 * 
 * @author 	pengpeng
 * @date   		2017年7月19日 上午10:25:28
 * @version 	1.0
 */
public enum AppVideoCrawlTaskType {

	EMPHASIS_TASK("1", "重点"), NOT_EMPHASIS_TASK("2", "普通"), GUANGDIAN_APP_TASK("3", "广电"), DOUYIN_APP_TASK("4", "抖音");
	
	private String typeCode;
	
	private String typeName;

	private AppVideoCrawlTaskType(String typeCode, String typeName) {
		this.typeCode = typeCode;
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public static AppVideoCrawlTaskType getTaskType(String typeCode) {
		for(AppVideoCrawlTaskType em : values()){
			if(em.getTypeCode().equals(typeCode)){
				return em;
			}
		}
		return null;
	}
	
}
