package com.certus.ivma.job;

import com.certus.ivma.service.AppVideoCrawlHostService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * APP抓取主机KeepAlive检测定时任务
 *
 * 定时检测IVMA_APPVIDEO_CRAWL_HOST.KEEPALIVE_TIME与数据库sysdate之间的差值,当大于某个值时,意味着该机器的active状态为0(非活动状态)
 *
 * Created by 123 on 2019/2/21.
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Order(value = 6)   //指定启动方法执行的顺序，越小优先级越高
public class CrawlHostKeepAliveJob {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlHostKeepAliveJob.class);

    @Autowired
    private AppVideoCrawlHostService appVideoCrawlHostService;

    @Value("${job.crawlHostKeepAlive.keepAliveTimes}")
    private Integer keepAliveTimes;

    @Scheduled(cron = "${job.crawlHostKeepAlive.cronExpression}")
    private void configureTasks() {
        LOG.info(">>> APP抓取主机KeepAlive检测定时任务开始... " + LocalDateTime.now());
        DateTime nowTime = new DateTime();
        appVideoCrawlHostService.updateActiveByKeepAliveTime(keepAliveTimes);
        LOG.debug("<<< APP抓取主机KeepAlive检测定时任务开始..." + LocalDateTime.now());
    }

}