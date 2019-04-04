package com.certus.ivma.mapper;

import com.certus.ivma.entity.AppVideoCrawlHost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppVideoCrawlHostMapper {

    AppVideoCrawlHost selectNotActiveCrawlTaskHost(@Param("taskType")String taskType);

    public void  mergeCrawlHost(AppVideoCrawlHost crawlHost);

    public AppVideoCrawlHost selectCrawlHostByIp(String hostIp);

    public int updateKeepAliveTime(@Param("hostIp") String hostIp,@Param("nowPollSize")  Integer nowPollSize);

    void updateActiveByKeepAliveTime(Integer keepAliveTimes);
}
