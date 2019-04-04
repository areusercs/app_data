package com.certus.ivma.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by 123 on 2019/3/6.
 */
@Configuration//表示这是个配置类
@EnableAsync//线程池的配置类
public class ExecutorConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ExecutorConfig.class);

    @Value("${thread_pool.schedule_executor.core_pool_size}")
    private int schedule_corePoolSize;
    @Value("${thread_pool.schedule_executor.max_pool_size}")
    private int schedule_maxPoolSize;
    @Value("${thread_pool.schedule_executor.queue_capacity}")
    private int schedule_queueCapacity;
    @Value("${thread_pool.schedule_executor.name_prefix}")
    private String schedule_namePrefix;
    @Value("${thread_pool.schedule_executor.keep_alive_seconds}")
    private int schedule_keepAliveSeconds;

    @Value("${thread_pool.execute_executor.core_pool_size}")
    private int execute_corePoolSize;
    @Value("${thread_pool.execute_executor.max_pool_size}")
    private int execute_maxPoolSize;
    @Value("${thread_pool.execute_executor.queue_capacity}")
    private int execute_queueCapacity;
    @Value("${thread_pool.execute_executor.name_prefix}")
    private String execute_namePrefix;
    @Value("${thread_pool.execute_executor.keep_alive_seconds}")
    private int execute_keepAliveSeconds;

    @Bean
    public VisiableThreadPoolTaskExecutor asyncServiceExecutor() {
        LOG.info("start asyncServiceExecutor");
        VisiableThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
//        ThreadPoolExecutor pool=new ThreadPoolExecutor(1,1, 1, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        //线程池核心线程数
        executor.setCorePoolSize(schedule_corePoolSize);
        //线程池最大线程数
        executor.setMaxPoolSize(schedule_maxPoolSize);
        //缓冲队列
        executor.setQueueCapacity(schedule_queueCapacity);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(schedule_namePrefix);
        //线程空闲时间 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
        //如果allowCoreThreadTimeout=true，则会直到线程数量=0
        executor.setKeepAliveSeconds(schedule_keepAliveSeconds);
        executor.setAllowCoreThreadTimeOut(true);//是否允许核心线程超时
        executor.setWaitForTasksToCompleteOnShutdown(true);//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setAwaitTerminationSeconds(60);//用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住

        /*
        rejection-policy：当pool已经达到max size的时候，如何处理新任务

        四种预定义的处理程序策略：
        (1) 默认的ThreadPoolExecutor.AbortPolicy
                处理程序遭到拒绝将抛出运行时RejectedExecutionException;
        (2) ThreadPoolExecutor.CallerRunsPolicy
                不在新线程中执行任务，而是有调用者所在的线程来执行
                线程调用运行该任务的 execute 本身。此策略提供简单的反馈控制机制，能够减缓新任务的提交速度
        (3) ThreadPoolExecutor.DiscardPolicy
                不能执行的任务将被删除;
        (4) ThreadPoolExecutor.DiscardOldestPolicy
                如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）。
        */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        //执行初始化
        executor.initialize();
        return executor;
    }

    @Bean
    public VisiableThreadPoolTaskExecutor taskExecute() {
        LOG.info("start taskExecute");
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new NamedThreadFactory("CRAWL-TASK-EXECUTOR-"));

        VisiableThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //线程池核心线程数
        executor.setCorePoolSize(execute_corePoolSize);
        //线程池最大线程数
        executor.setMaxPoolSize(execute_maxPoolSize);
        //缓冲队列
        executor.setQueueCapacity(execute_queueCapacity);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(execute_namePrefix);

        //线程空闲时间 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
        //如果allowCoreThreadTimeout=true，则会直到线程数量=0
        executor.setKeepAliveSeconds(execute_keepAliveSeconds);
        executor.setAllowCoreThreadTimeOut(true);//是否允许核心线程超时
        executor.setWaitForTasksToCompleteOnShutdown(true);//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setAwaitTerminationSeconds(60);//用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //执行初始化
        executor.initialize();

        return executor;
    }
}
