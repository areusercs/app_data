package com.certus.ivma.service;

import com.certus.ivma.entity.AppVideoCrawlHost;
import org.springframework.stereotype.Service;

/**
 * Created by 123 on 2019/2/21.
 */
@Service
public interface AppVideoCrawlHostService {

    void registerCrawlHost(AppVideoCrawlHost crawlHost);

    AppVideoCrawlHost getCrawlHostByIp(String hostIp);

    void crawlHostKeepAlive(String hostIp,Integer nowPollSize);

    void updateActiveByKeepAliveTime(Integer keepAliveTimes);
}
