package com.certus.ivma.em;

/**
 * 调度任务类型
 * 
 * @author 	pengpeng
 * @date   		2017年7月19日 上午10:25:28
 * @version 	1.0
 */
public enum AppVideoCrawlTaskDispatchType {

	TOUTINE_TASK("1", "常规"), EMERGENCY_TASK("2", "应急"), OTHER_TASK("3", "其他待定");

	private String typeCode;

	private String typeName;

	private AppVideoCrawlTaskDispatchType(String typeCode, String typeName) {
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
	
	public static AppVideoCrawlTaskDispatchType getTaskType(String typeCode) {
		for(AppVideoCrawlTaskDispatchType em : values()){
			if(em.getTypeCode().equals(typeCode)){
				return em;
			}
		}
		return null;
	}
	
}
