package com.certus.ivma.config;

import com.certus.ivma.mapper.AppVideoCrawlTaskTypeMapper;
import com.certus.ivma.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

/**
 * Created by 123 on 2019/3/25.
 */
//@Configuration
//@EnableScheduling//加上此注解才可动态改变运行时间
public class CompleteScheduleConfig implements SchedulingConfigurer {
    @Autowired
    private AppVideoCrawlTaskTypeMapper appVideoCrawlTaskTypeMapper;

    /**
     * 执行定时任务.  只能修改一个 ，批量的修改还没实现 暂时不用
     */
   @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        List<AppVideoCrawlTaskType> crawlTypeList = appVideoCrawlTaskTypeMapper.getCrawlType();
//        for (AppVideoCrawlTaskType appVideoCrawlTaskType : crawlTypeList) {

            taskRegistrar.addTriggerTask(
                    //1.添加任务内容(Runnable)
                    () -> System.out.println("执行定时任务: " + LocalDateTime.now().toLocalTime()),


                    //2.设置执行周期(Trigger)
                    triggerContext -> {
                        //2.1 从数据库获取执行周期
//                        String cron = appVideoCrawlTaskType.getCrawlTaskCron();
                        String cron = appVideoCrawlTaskTypeMapper.getCrawlType().get(0).getCrawlTaskCron();
                        //2.2 合法性校验.
                        if (StringUtils.isEmpty(cron)) {
                            // Omitted Code ..
                        }
                        //2.3 返回执行周期(Date)
                        return new CronTrigger(cron).nextExecutionTime(triggerContext);
                    }
            );
//        }

    }

}
