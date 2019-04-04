package com.certus.ivma.service.impl;

import com.certus.ivma.config.VisiableThreadPoolTaskExecutor;
import com.certus.ivma.entity.AppVideoCrawlLog;
import com.certus.ivma.entity.AppVideoCrawlTask;
import com.certus.ivma.entity.TaskExecuteContext;
import com.certus.ivma.service.AppVideoCrawlTaskService;
import com.certus.ivma.service.AsyncService;
import com.certus.ivma.service.TaskExecuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by 123 on 2019/3/6.
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncServiceImpl.class);

    //这个线程池是专门用来执行脚本的
    @Autowired
    private TaskExecuteService taskExecuteService;
    //这个线程池是用来接收任务的，在这里引入只是用来取 getPoolCapacity() 当前线程池使用线程数
    @Resource(name = "asyncServiceExecutor")
    private VisiableThreadPoolTaskExecutor asyncServiceExecutor;

    @Autowired
    private AppVideoCrawlTaskService appVideoCrawlTaskService;

    @Async("asyncServiceExecutor")//asyncServiceExecutor即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    @Override
    public void executeAsync(TaskExecuteContext taskExecuteContext) {
        try{
            //修改任务状态为2，执行中，添加任务log数据，更新HOST 使用量
            AppVideoCrawlTask crawlTask = taskExecuteContext.getCrawlTask();
            AppVideoCrawlLog   crawlLog = appVideoCrawlTaskService.beginTask(crawlTask,asyncServiceExecutor.getPoolCapacity());
            taskExecuteContext.setCrawlLog(crawlLog);
            if(taskExecuteContext!=null){
                Future<AppVideoCrawlLog> futureTask = null;
                try {
                    //
                    futureTask = taskExecuteService.executeAsync(taskExecuteContext);
                    //指定时间内抓取任务未完成则不再等待
                    futureTask.get(taskExecuteContext.getMaxTaskExecuteTimes(), TimeUnit.SECONDS);
                }catch (Throwable e){
                    String message = null;
                    if(e instanceof TimeoutException){
                        message = "执行APP视频节目抓取脚本超时!";
                    }else{
                        message = e.getMessage();
                    }
                    LOG.error(message);
                    crawlLog.setSuccess(false);
                    crawlLog.setMessage(message);

                }finally {
                    //修改任务状态，完善任务log表
                    appVideoCrawlTaskService.endRunningTask(crawlTask, crawlLog);
                    System.gc();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            LOG.error(taskExecuteContext.getCrawlTask().getAppEngName()+">>>"+e.getMessage());
        }

    }
/*
    public void executeAsync(TaskExecuteContext taskExecuteContext) {
        try{
            //修改任务状态为2，执行中，添加任务log数据，更新HOST 使用量
            AppVideoCrawlTask crawlTask = taskExecuteContext.getCrawlTask();
            AppVideoCrawlLog   crawlLog = appVideoCrawlTaskService.beginTask(crawlTask,asyncServiceExecutor.getPoolCapacity());
            taskExecuteContext.setCrawlLog(crawlLog);
            if(taskExecuteContext!=null){
                Future<String> futureTask = null;
                try {
                    //
                    futureTask = taskExecuteService.executeAsync(taskExecuteContext);
                    //指定时间内抓取任务未完成则不再等待
                    futureTask.get(taskExecuteContext.getMaxTaskExecuteTimes(), TimeUnit.SECONDS);
                }catch (Throwable e){
                    String message = null;
                    if(e instanceof TimeoutException){
                        message = "执行APP视频节目抓取脚本超时!";
                    }else{
                        message = e.getMessage();
                    }
                    LOG.error(message);
                    crawlLog.setSuccess(false);
                    crawlLog.setMessage(message);

                }finally {
                    //修改任务状态，完善任务log表
                    appVideoCrawlTaskService.endRunningTask(crawlTask, crawlLog);
                    System.gc();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            LOG.error(taskExecuteContext.getCrawlTask().getAppEngName()+">>>"+e.getMessage());
        }

    }
    */
}
