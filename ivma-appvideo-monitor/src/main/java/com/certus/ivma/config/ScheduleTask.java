package com.certus.ivma.config;

import com.certus.ivma.em.AppVideoCrawlTaskDispatchType;
import com.certus.ivma.entity.AppVideoCrawlTaskType;
import com.certus.ivma.service.AppVideoCrawlTaskService;
import com.certus.ivma.service.AppVideoCrawlTaskTypeService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 123 on 2019/3/25.
 */
//@Configuration
//@Component // 此注解必加
//@EnableScheduling // 此注解必加
public class ScheduleTask {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private AppVideoCrawlTaskTypeService appVideoCrawlTaskTypeService;
    @Autowired
    private AppVideoCrawlTaskService appVideoCrawlTaskService;

    //批量创建各种类型的任务  相当于创建所有任务 失败
    public void sayHello(){
        LOG.info("Hello world, i'm the king of the world!!!");
        List<AppVideoCrawlTaskType> crawlTypeList = appVideoCrawlTaskTypeService.getCrawlType();
        for (AppVideoCrawlTaskType appVideoCrawlTaskType : crawlTypeList) {
            LOG.info(">>> "+appVideoCrawlTaskType.getAppCrawlTypeName()+"APP抓取任务批量创建定时任务开始... " + LocalDateTime.now());
            DateTime nowTime = new DateTime();
            appVideoCrawlTaskService.batchCreateCrawlTask( nowTime.toString("yyyyMMdd"), appVideoCrawlTaskType, AppVideoCrawlTaskDispatchType.TOUTINE_TASK);
            LOG.info("<<< "+appVideoCrawlTaskType.getAppCrawlTypeName()+"APP抓取任务批量创建定时任务结束..." + LocalDateTime.now());
        }
    }
}