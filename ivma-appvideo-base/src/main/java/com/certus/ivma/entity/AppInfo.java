package com.certus.ivma.entity;

/**
 * 手机APP信息
 * 
 * @author 	pengpeng
 * @date   		2017年6月15日 下午1:11:17
 * @version 	1.0
 */
public class AppInfo {

	/** ID */
	private Long id;
	
	/** APP名称 */
	private String name;
	
	/** APP英文名 */
	private String engName;
	
	/** APP类型 1：IOS,2：安卓,3:windows phone */
	private String appType;
	
	/** APP图标 */
	private String appIcon;
	
	/** 接入方式 */
	private String accessMethod;
	
	/** APP唯一标识 */
	private String appIden;
	
	/** 1:重点  2：常规  3:动态 */
	private String appClassify;
	
	/** 是否持证网站APP  1:是,0或null:否 */
	private String avspFlag;
	
	/** APP星级 1：一星,2：二星,3：三星,4：四星,5：五星 */
	private String appLevel;
	
	/** APP版本 */
	private String appVersion;
	
	/** APP所属公司归属省份 */
	private Long locationId;
	
	private String liveFlag;
	
	/** 软件破解的脚本版本 */
	private String scriptVersion;
	
	/** 软件版本更新时间 */
	private String versionUpdateTime;
	
	/** 插入时间 */
	private String insertTime;
	
	private Boolean emphasisApp;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public String getAccessMethod() {
		return accessMethod;
	}

	public void setAccessMethod(String accessMethod) {
		this.accessMethod = accessMethod;
	}

	public String getAppIden() {
		return appIden;
	}

	public void setAppIden(String appIden) {
		this.appIden = appIden;
	}

	public String getAppClassify() {
		return appClassify;
	}

	public void setAppClassify(String appClassify) {
		this.appClassify = appClassify;
	}

	public String getAvspFlag() {
		return avspFlag;
	}

	public void setAvspFlag(String avspFlag) {
		this.avspFlag = avspFlag;
	}

	public String getAppLevel() {
		return appLevel;
	}

	public void setAppLevel(String appLevel) {
		this.appLevel = appLevel;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLiveFlag() {
		return liveFlag;
	}

	public void setLiveFlag(String liveFlag) {
		this.liveFlag = liveFlag;
	}

	public String getScriptVersion() {
		return scriptVersion;
	}

	public void setScriptVersion(String scriptVersion) {
		this.scriptVersion = scriptVersion;
	}

	public String getVersionUpdateTime() {
		return versionUpdateTime;
	}

	public void setVersionUpdateTime(String versionUpdateTime) {
		this.versionUpdateTime = versionUpdateTime;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public Boolean getEmphasisApp() {
		return emphasisApp;
	}

	public void setEmphasisApp(Boolean emphasisApp) {
		this.emphasisApp = emphasisApp;
	}

	@Override
	public String toString() {
		return "AppInfo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", engName='" + engName + '\'' +
				", appType='" + appType + '\'' +
				", appIcon='" + appIcon + '\'' +
				", accessMethod='" + accessMethod + '\'' +
				", appIden='" + appIden + '\'' +
				", appClassify='" + appClassify + '\'' +
				", avspFlag='" + avspFlag + '\'' +
				", appLevel='" + appLevel + '\'' +
				", appVersion='" + appVersion + '\'' +
				", locationId=" + locationId +
				", liveFlag='" + liveFlag + '\'' +
				", scriptVersion='" + scriptVersion + '\'' +
				", versionUpdateTime='" + versionUpdateTime + '\'' +
				", insertTime='" + insertTime + '\'' +
				", emphasisApp=" + emphasisApp +
				'}';
	}
}
