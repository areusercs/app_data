package com.certus.ivma.job;

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
@Order(value = 5)   //指定启动方法执行的顺序，越小优先级越高
public class FailFastCrawlTaskReScheduleJob implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(FailFastCrawlTaskReScheduleJob.class);

    @Value("${job.failFastCrawlTaskReSchedule.runningTimeLimit}")
    private int runningTimeLimit = 30;
    @Value("${job.failFastCrawlTaskReSchedule.runAfterStartup}")
    boolean runAfterStartup;

    @Autowired
    private AppVideoCrawlTaskService appVideoCrawlTaskService;

    @Scheduled(cron = "${job.failFastCrawlTaskReSchedule.cronExpression}")
    private void configureTasks() {
        LOG.info(">>> 重置执行快速失败的任务为待分配状态的任务定时任务开始... " + LocalDateTime.now());
        DateTime nowTime = new DateTime();
        //执行状态为失败success = 0 并且 IVMA_APPVIDEO_CRAWL_TASK表中运行时间小于limitTimes 秒的任务
        appVideoCrawlTaskService.reScheduleFailFastCrawlTask( nowTime.toString("yyyyMMdd"), runningTimeLimit);
        LOG.debug("<<< 重置执行快速失败的任务为待分配状态的任务定时任务结束..." + LocalDateTime.now());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("-------------->" + "项目启动，重置执行快速失败的任务为待分配状态的任务定时任务开始=" + new Date());
        if(runAfterStartup){
            configureTasks();
            LOG.info("执行完毕");
        }
    }
}