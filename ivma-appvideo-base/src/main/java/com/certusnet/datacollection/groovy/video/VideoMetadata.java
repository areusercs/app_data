package com.certusnet.datacollection.groovy.video;

import java.util.Date;

/**
 * 视频元数据
 * 
 * @author lig
 * 
 */
public class VideoMetadata {
	private String id;// 节目id
	private String name;// 节目名称
	private String directors;// 导演
	private String actors;// 演员
	private String year;// 影片年份
	private String area;
	private String desc;// 节目简介
	private String channel;// 频道
	private String type;// 类型
	
	private Long playCount;// 播放次数
	private Long commentCount;// 评论次数
	private Long likeCount;// 喜欢次数
	private Long unLikeCount;// 不喜欢次数
	private Long shareCount;// 分享转发次数
	
	private String uploadUserId;// 上传者抖音号(或者其他平台上的id)
	private String uploadUserName;// 上传者昵称
	private String uploadUserImg;// 上传者头像
	private String videoDuration;// 视频时长
	
	
	
	private String publisher;// 上传者，发布者
	private Date releaseTime;// 发布时间
	private String isVideo;// 是否为音视频
	private String hitKeyWord;// 包含的关键词
	
	private String posterUrl;// 海报
	/**
	 * url的地址类型 1、直接播放，2、需要转换（时效地址），3、不支持播放（获取地址失败）；
	 */
	private int urlType;
	/**
	 * 播放器可直接播放的地址。或时效地址
	 */
	private String url;

	/**
	 * APP节目分享web地址
	 */
	private String shareUrl;
	/**
	 * APP节目其他web地址
	 */
	private String webUrl;
	/**
	 * 其他参数，为标准json格式，目的为进行URL转换时需要的参数
	 */
	private String extra;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(String isVideo) {
		this.isVideo = isVideo;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Long playCount) {
		this.playCount = playCount;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public int getUrlType() {
		return urlType;
	}

	public void setUrlType(int urlType) {
		this.urlType = urlType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	public void setHitKeyWord(String hitKeyWord) {
		this.hitKeyWord = hitKeyWord;
	}
	public String getHitKeyWord() {
		return hitKeyWord;
	}

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public Long getShareCount() {
		return shareCount;
	}

	public void setShareCount(Long shareCount) {
		this.shareCount = shareCount;
	}
	

	public String getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(String uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	public String getUploadUserName() {
		return uploadUserName;
	}

	public void setUploadUserName(String uploadUserName) {
		this.uploadUserName = uploadUserName;
	}

	public String getUploadUserImg() {
		return uploadUserImg;
	}

	public void setUploadUserImg(String uploadUserImg) {
		this.uploadUserImg = uploadUserImg;
	}

	
	public String getVideoDuration() {
		return videoDuration;
	}

	public void setVideoDuration(String videoDuration) {
		this.videoDuration = videoDuration;
	}

	public Long getUnLikeCount() {
		return unLikeCount;
	}

	public void setUnLikeCount(Long unLikeCount) {
		this.unLikeCount = unLikeCount;
	}

	@Override
	public String toString() {
		return "VideoMetadata [id=" + id + ", name=" + name + ", directors="
				+ directors + ", actors=" + actors + ", year=" + year
				+ ", area=" + area + ", desc=" + desc + ", channel=" + channel
				+ ", type=" + type + ", playCount=" + playCount
				+ ", commentCount=" + commentCount + ", likeCount=" + likeCount
				+ ", unLikeCount=" + unLikeCount + ", shareCount=" + shareCount
				+ ", uploadUserId=" + uploadUserId + ", uploadUserName="
				+ uploadUserName + ", uploadUserImg=" + uploadUserImg
				+ ", videoDuration=" + videoDuration + ", publisher="
				+ publisher + ", releaseTime=" + releaseTime + ", isVideo="
				+ isVideo + ", hitKeyWord=" + hitKeyWord + ", posterUrl="
				+ posterUrl + ", urlType=" + urlType + ", url=" + url
				+ ", shareUrl=" + shareUrl + ", webUrl=" + webUrl + ", extra="
				+ extra + "]";
	}

	
	
}
