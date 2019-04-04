package com.certus.ivma.config;

import com.certus.ivma.entity.AppVideoCrawlTaskType;
import com.certus.ivma.mapper.AppVideoCrawlTaskTypeMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2019/3/25.
 */
//@Configuration
//@EnableScheduling
//@Component
public class ScheduleRefreshDatabase {

    @Autowired
    private AppVideoCrawlTaskTypeMapper appVideoCrawlTaskTypeMapper;

    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Scheduled(fixedRate = 5000) // 每隔5s查库，并根据查询结果决定是否重新设置定时任务
    public void scheduleUpdateCronTrigger() throws SchedulerException {
        String currentCron = ((CronTrigger) scheduler.getTrigger(cronTrigger.getKey())).getCronExpression();// 当前Trigger使用的
        List<String> cronList = new ArrayList<>();
        //获取数据库中的可用的任务类型
        String searchCron = "";
                List<AppVideoCrawlTaskType> taskTypeList =  appVideoCrawlTaskTypeMapper.getCrawlType();
        for (AppVideoCrawlTaskType taskType : taskTypeList) {
            searchCron = taskType.getCrawlTaskCron();// 任务类型i的cron
            System.out.println(currentCron);
            System.out.println(searchCron);
        }

        if (currentCron.equals(searchCron)) {
            // 如果当前使用的cron表达式和从数据库中查询出来的cron表达式一致，则不刷新任务
        } else {
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
            // 按新的cronExpression表达式重新构建trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey()).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
            currentCron = searchCron;
        }
    }

}
