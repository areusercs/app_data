package com.certus.ivma.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by 123 on 2019/3/6.
 */
@Component("visiableThreadPoolTaskExecutor")
public class VisiableThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(VisiableThreadPoolTaskExecutor.class);

    /**
     * 获取当前线程池中的  活跃线程数 和 阻塞队列中 的线程数之和
     * @return
     */
    public Integer getPoolCapacity(){
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        Integer activeCount = threadPoolExecutor.getActiveCount();
        Integer queueCount  = threadPoolExecutor.getQueue().size();
        return queueCount+activeCount;
    }
    public void showThreadPoolInfo(String prefix){
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        if(null==threadPoolExecutor){
            return;
        }
        LOG.info("{}, {}, 任务总数 [{}], 已完成数 [{}], 活跃线程数 [{}], 队列当前大小 [{}], 线程池当前大小 [{}], 核心池容量 [{}],最大容量 [{}]," ,
                this.getThreadNamePrefix(),
                prefix,

                threadPoolExecutor.getTaskCount(),//任务总数
                threadPoolExecutor.getCompletedTaskCount(),//已完成数
                threadPoolExecutor.getActiveCount(),//活跃线程数
                threadPoolExecutor.getQueue().size(),//队列当前大小
                threadPoolExecutor.getPoolSize(),//线程池当前大小
                threadPoolExecutor.getCorePoolSize(),//核心池容量
                threadPoolExecutor.getMaximumPoolSize() //最大容量
        );
    }

    public String showThreadPoolInfo(){
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        String info =   "任务总数："+threadPoolExecutor.getTaskCount()+","+
                        "已完成数："+threadPoolExecutor.getCompletedTaskCount()+","+
                        "活跃线程数：" +threadPoolExecutor.getActiveCount()+","+
                        "队列大小：" +threadPoolExecutor.getQueue().size()+""+
                        "";
        return info;
    }

    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo("1. do execute");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo("2. do execute");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo("1. do submit");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo("2. do submit");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo("1. do submitListenable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo("2. do submitListenable");
        return super.submitListenable(task);
    }
}
