package com.certus.ivma.job;

import com.certus.ivma.em.AppVideoCrawlTaskStatus;
import com.certus.ivma.entity.AppVideoCrawlTask;
import com.certus.ivma.entity.AppVideoCrawlTaskType;
import com.certus.ivma.entity.RequestInfo;
import com.certus.ivma.entity.ReturnInfo;
import com.certus.ivma.service.AppVideoCrawlTaskService;
import com.certus.ivma.service.AppVideoCrawlTaskTypeService;
import com.certus.ivma.service.CallServiceCrawl;
import com.certus.ivma.service.CrawlService;
import com.certus.ivma.util.DateTimeUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 123 on 2019/2/21.
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class AppCrawlTaskDispatch {

    private static final Logger LOG = LoggerFactory.getLogger(AppCrawlTaskDispatch.class);

    @Autowired
    private CallServiceCrawl callServiceCrawl;//FeignClient
    @Autowired
    private CrawlService crawlService;//ribbon + restTemplate

    @Autowired
    private AppVideoCrawlTaskService appVideoCrawlTaskService;
    @Autowired
    private AppVideoCrawlTaskTypeService appVideoCrawlTaskTypeService;

    @Scheduled(cron = "${job.appCrawlTaskDispatch.cronExpression}")
    private void configureTasks() {
        LOG.info(">>> APP抓取任务调度任务开始... " + LocalDateTime.now());
        List<AppVideoCrawlTaskType> crawlTypeList = appVideoCrawlTaskTypeService.getCrawlType();
        for (AppVideoCrawlTaskType appVideoCrawlTaskType : crawlTypeList) {
            DateTime nowTime = new DateTime();
            //根据类型查询今天所有的待分配任务
            List<AppVideoCrawlTask> appVideoCrawlTaskList = appVideoCrawlTaskService.dispatchCrawlTask(nowTime.toString("yyyyMMdd"), appVideoCrawlTaskType);
            if (appVideoCrawlTaskList.size() > 0) {
                for (AppVideoCrawlTask appVideoCrawlTask : appVideoCrawlTaskList) {
                    //修改任务状态 为 已分配待运行 0->1
                    appVideoCrawlTask.setScheduleTime(DateTimeUtils.formatNow());
                    appVideoCrawlTask.setStatus(AppVideoCrawlTaskStatus.TASK_SCHEDULED.getStatusCode());//已分配待运行
                    appVideoCrawlTaskService.updataCrawlTaskStatus(appVideoCrawlTask);
                    //分配任务
                    ReturnInfo returns = null;
                    //根据任务类型自动调用相应类型的抓取服务
                    returns = crawlService.crawlService(new RequestInfo(appVideoCrawlTask.getAppEngName(), "myStorageAddress", appVideoCrawlTask));
                    //舍弃这种模式，不够灵活
//                    switch (appVideoCrawlTaskType.getTypeCode()) {
//                        case "1":
//                            returns = callServiceCrawl.sayCrawlFromClientOne(new RequestInfo(appVideoCrawlTask.getAppEngName(), "myStorageAddress", appVideoCrawlTask));
//                            break;
//                        case "2":
//                            returns = callServiceCrawl.sayCrawlFromClientOne(new RequestInfo(appVideoCrawlTask.getAppEngName(), "myStorageAddress", appVideoCrawlTask));
//                            break;
//                        case "3":
//                            returns = callServiceCrawl.sayCrawlFromClientOne(new RequestInfo(appVideoCrawlTask.getAppEngName(), "myStorageAddress", appVideoCrawlTask));
//                            break;
//                        case "4":
//                            LOG.info("调用抖音app");
//                            returns = callServiceCrawl.sayCrawlFromClientOne(new RequestInfo(appVideoCrawlTask.getAppEngName(), "myStorageAddress", appVideoCrawlTask));
//                            break;
//                        default:
//                            LOG.error("<<< 未知的任务类型..." + appVideoCrawlTaskType.getTypeCode());
//                            break;
//                    }

                    //解析返回的结果
                    if (returns.getIsSuccess()) {
                        LOG.info(">>> APP脚本调度成功： " + appVideoCrawlTask.toString());
                        //修改任务分配机器
//                      appVideoCrawlTaskService.updataCrawlTaskScheduleHostByTaskId(returns.getIp(),appVideoCrawlTask.getTaskId());
                    } else {
                        //手动回滚任务状态 1->0
                        LOG.info(">>> APP脚本调度失败：{}  ---  失败原因：{} " , appVideoCrawlTask.toString(),returns.getInfo());
                        appVideoCrawlTask.setScheduleTime(null);//这里实际上没用，mapper.xml中不会更新调度时间，因为是null
                        appVideoCrawlTask.setStatus(AppVideoCrawlTaskStatus.TASK_WAIT_SCHEDULING.getStatusCode());//待分配
                        appVideoCrawlTaskService.updataCrawlTaskStatus(appVideoCrawlTask);
                    }

                }
            }
        }
        LOG.info("<<< APP抓取任务调度任务结束..." + LocalDateTime.now());
    }
}