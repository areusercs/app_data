package com.certus.ivma.controller;

import com.certus.ivma.config.VisiableThreadPoolTaskExecutor;
import com.certus.ivma.entity.RequestInfo;
import com.certus.ivma.entity.ReturnInfo;
import com.certus.ivma.entity.TaskExecuteContext;
import com.certus.ivma.service.AsyncService;
import com.certus.ivma.util.HostUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by 123 on 2019/3/5.
 */
@RestController
@RefreshScope
public class CrawlController {

    private static final Logger LOG = LoggerFactory.getLogger(CrawlController.class);

    @Autowired
    private AsyncService asyncService;

    @Resource(name = "asyncServiceExecutor")
    private VisiableThreadPoolTaskExecutor asyncServiceExecutor;

    @Value("${server.port}")
    String port;

    @Value("${thread_pool.schedule_executor.max_pool_size}")
    private Integer schedule_maxPoolSize;
    @Value("${thread_pool.schedule_executor.queue_capacity}")
    private Integer schedule_queueCapacity;

    @Value("${spring.cloud.config.uri}")
    private String config_uri;
    @Value("${app.shell.path}")
    private String appShellPath;
    @Value("${constants.task.execute.max.second}")
    private long maxTaskExecuteTimes;

    /**
     * @param requestInfo
     * @return
     * @RequestParam(value = "storageAddress", defaultValue = "222") String storageAddress
     */
    @RequestMapping(value = "/receiveMission ", method = RequestMethod.POST, consumes = "application/json")
    public ReturnInfo receiveMission(@RequestBody RequestInfo requestInfo) {
        ReturnInfo info = new ReturnInfo();
        LOG.info(">>>收到任务：" + requestInfo.getAppVideoCrawlTask().getAppName());
        //处理任务
        Integer poolCapacity = asyncServiceExecutor.getPoolCapacity();//线程池中 活跃线程数+阻塞队列线程数，大于此值将会舍弃任务
        if (poolCapacity < schedule_maxPoolSize + schedule_queueCapacity) {//还没有满，处理任务
            LOG.info(">>>执行任务：" + requestInfo.getAppVideoCrawlTask().getAppName());
            //调用service层的任务
            asyncService.executeAsync(new TaskExecuteContext(requestInfo.getAppVideoCrawlTask(),maxTaskExecuteTimes,appShellPath));
            info.setIp(HostUtil.getLocalHostIp());//返回IP地址
            info.setPort(port);
            info.setIsSuccess(true);
            info.setInfo("success");
        } else {                              //线程池满了，分配失败
            LOG.info(">>>拒绝任务：" + requestInfo.getAppVideoCrawlTask().getAppName());
            info.setIsSuccess(false);
            info.setInfo("filed 线程池满了，分配失败");
        }
        return info;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return asyncServiceExecutor.showThreadPoolInfo();
    }

    @RequestMapping(value = "/h")
    public Integer h() {
        return schedule_maxPoolSize;
    }

}
