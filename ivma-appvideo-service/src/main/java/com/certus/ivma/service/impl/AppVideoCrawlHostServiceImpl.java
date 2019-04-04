package com.certus.ivma.service.impl;

import com.certus.ivma.entity.AppVideoCrawlHost;
import com.certus.ivma.mapper.AppVideoCrawlHostMapper;
import com.certus.ivma.service.AppVideoCrawlHostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 123 on 2019/2/21.
 */
@Service
public class AppVideoCrawlHostServiceImpl implements AppVideoCrawlHostService {

    private static final Logger LOG = LoggerFactory.getLogger(AppVideoCrawlHostServiceImpl.class);

    @Autowired
    private AppVideoCrawlHostMapper appVideoCrawlHostMapper;

    @Override
    @Transactional(rollbackFor=Exception.class, propagation= Propagation.REQUIRED)
    public void registerCrawlHost(AppVideoCrawlHost crawlHost) {
        appVideoCrawlHostMapper.mergeCrawlHost(crawlHost);
    }

    @Override
    public AppVideoCrawlHost getCrawlHostByIp(String hostIp) {
        return appVideoCrawlHostMapper.selectCrawlHostByIp(hostIp);
    }

    @Override
    public void crawlHostKeepAlive(String hostIp,Integer nowPollSize) {
        appVideoCrawlHostMapper.updateKeepAliveTime(hostIp,nowPollSize);
    }

    @Override
    public void updateActiveByKeepAliveTime(Integer keepAliveTimes) {
        appVideoCrawlHostMapper.updateActiveByKeepAliveTime(keepAliveTimes);
    }
}


