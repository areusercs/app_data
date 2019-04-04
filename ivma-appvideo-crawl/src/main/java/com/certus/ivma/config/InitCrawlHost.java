package com.certus.ivma.config;

import com.certus.ivma.entity.AppVideoCrawlHost;
import com.certus.ivma.service.AppVideoCrawlHostService;
import com.certus.ivma.util.DateTimeUtils;
import com.certus.ivma.util.HostUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 123 on 2019/3/14.
 */
@Component
@Order(value = 1)   //指定启动方法执行的顺序，越小优先级越高
public class InitCrawlHost implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(InitCrawlHost.class);

    private final ScheduledExecutorService crawlHostKeepAliveExecutorService = Executors.newScheduledThreadPool(1);

    @Autowired
    private AppVideoCrawlHostService appVideoCrawlHostService;

    @Resource(name = "asyncServiceExecutor")
    private VisiableThreadPoolTaskExecutor asyncServiceExecutor;
    @Resource(name = "taskExecute")
    private VisiableThreadPoolTaskExecutor taskExecute;

    @Value("${constants.host.keepalive.second}")
    private long crawlHostKeepAliveSecond;

    @Value("${thread_pool.schedule_executor.max_pool_size}")
    private Integer schedule_maxPoolSize;
    @Value("${thread_pool.schedule_executor.queue_capacity}")
    private Integer schedule_queueCapacity;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("项目启动，注册本机服务ip地址到数据库");
        registerCurrentCrawlHost();
        //定时修改本机的 IVMA_APPVIDEO_CRAWL_HOST.KEEPALIVE_TIME = sysdate, IVMA_APPVIDEO_CRAWL_HOST.ACTIVE = 1 活动的
        crawlHostKeepAliveExecutorService.scheduleAtFixedRate(new Runnable(){
            public void run() {
                String hostIp = HostUtil.getLocalHostIp();
                Integer poolCapacity = asyncServiceExecutor.getPoolCapacity();
                asyncServiceExecutor.showThreadPoolInfo("容量监控-当前状态：");
                taskExecute.showThreadPoolInfo("容量监控-当前状态：");
/*
                LOG.info("{}, 核心大小 [{}],任务总数 [{}], 已完成数 [{}], 活跃线程数 [{}], 队列大小 [{}]" ,
                        "CRAWL-TASK-EXECUTOR-",
                        taskExecute.getCorePoolSize(),
                        taskExecute.getTaskCount(),//任务总数
                        taskExecute.getCompletedTaskCount(),//已完成数
                        taskExecute.getActiveCount(),//活跃线程数
                        taskExecute.getQueue().size());//队列大小
*/
                LOG.info("最大容量+队列容量："+schedule_maxPoolSize+"+"+schedule_queueCapacity+"="+(schedule_maxPoolSize+schedule_queueCapacity)+" ; 当前使用："+poolCapacity);
                LOG.info("更新当前APP抓取主机KeepAlive服务");
                appVideoCrawlHostService.crawlHostKeepAlive(hostIp,poolCapacity);
            }
        }, 0, crawlHostKeepAliveSecond, TimeUnit.SECONDS);
    }
    protected void registerCurrentCrawlHost() {
        String nowTime = DateTimeUtils.formatNow();
        String hostIp = HostUtil.getLocalHostIp();
        AppVideoCrawlHost crawlHost = appVideoCrawlHostService.getCrawlHostByIp(hostIp);
        if(crawlHost != null){
            crawlHost.setActive(true);
            crawlHost.setUpdateTime(nowTime);
            crawlHost.setKeepAliveTime(nowTime);
            crawlHost.setMaxPollSize(schedule_maxPoolSize+schedule_queueCapacity);
            crawlHost.setNowPollSize(0);
        }else{
            crawlHost = new AppVideoCrawlHost();
            crawlHost.setHostIp(hostIp);
            crawlHost.setActive(true);
            crawlHost.setCrawlType("0");//这个抓取类型还没改
            crawlHost.setInsertTime(nowTime);
            crawlHost.setUpdateTime(nowTime);
            crawlHost.setKeepAliveTime(nowTime);
            crawlHost.setMaxPollSize(schedule_maxPoolSize+schedule_queueCapacity);
            crawlHost.setNowPollSize(0);
        }
        appVideoCrawlHostService.registerCrawlHost(crawlHost);
    }
}
