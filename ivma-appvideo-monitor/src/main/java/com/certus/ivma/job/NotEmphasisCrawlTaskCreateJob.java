package com.certus.ivma.job;

import com.certus.ivma.em.AppVideoCrawlTaskDispatchType;
import com.certus.ivma.em.AppVideoCrawlTaskType;
import com.certus.ivma.service.AppVideoCrawlTaskService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by 123 on 2019/2/21.
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Order(value = 2)   //指定启动方法执行的顺序，越小优先级越高
public class NotEmphasisCrawlTaskCreateJob implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(NotEmphasisCrawlTaskCreateJob.class);

    @Value("${job.notEmphasisAppCrawlTaskCreate.runAfterStartup}")
    boolean runAfterStartup;

    @Autowired
    private AppVideoCrawlTaskService appVideoCrawlTaskService;

    @Scheduled(cron = "${job.notEmphasisAppCrawlTaskCreate.cronExpression}")
    private void configureTasks() {
        LOG.info(">>> 普通APP抓取任务批量创建定时任务开始... " + LocalDateTime.now());
        DateTime nowTime = new DateTime();
        appVideoCrawlTaskService.batchCreateCrawlTask( nowTime.toString("yyyyMMdd"), AppVideoCrawlTaskType.NOT_EMPHASIS_TASK, AppVideoCrawlTaskDispatchType.TOUTINE_TASK);
        LOG.debug("<<< 普通APP抓取任务批量创建定时任务结束..." + LocalDateTime.now());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("-------------->" + "项目启动，准备创建普通APP抓取任务=" + new Date());
        if(runAfterStartup){
            configureTasks();
            LOG.info("执行完毕");
        }
    }
}