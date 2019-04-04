package com.certus.ivma;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by 123 on 2019/3/18.
 */
public class DiscardOldestPolicyDemo {

    private static final int THREADS_SIZE = 2;
    private static final int CAPACITY = 0;

    public static void main(String[] args) throws Exception {

//        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<Runnable>(CAPACITY));
//        // 设置线程池的拒绝策略为"DiscardOldestPolicy"
//        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
//
//        // 新建10个任务，并将它们添加到线程池中。
//        for (int i = 0; i < 10; i++) {
//            Runnable myrun = new MyRunnable("task-"+i);
//            pool.execute(myrun);
//        }
//        // 关闭线程池
//        pool.shutdown();

        ThreadPoolTaskExecutor pool = pool();
        // 新建10个任务，并将它们添加到线程池中。
        for (int i = 0; i < 10; i++) {
            Runnable myrun = new MyRunnable("task-"+i);
            pool.execute(myrun);
        }
        // 关闭线程池
        pool.shutdown();
    }

    public static ThreadPoolTaskExecutor pool(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池核心线程数
        executor.setCorePoolSize(2);
        //线程池最大线程数
        executor.setMaxPoolSize(2);
        //缓冲队列
        executor.setQueueCapacity(0);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("taskExecute");
        //线程空闲时间 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
        //如果allowCoreThreadTimeout=true，则会直到线程数量=0
        executor.setKeepAliveSeconds(60);
        executor.setAllowCoreThreadTimeOut(false);//是否允许核心线程超时
        executor.setWaitForTasksToCompleteOnShutdown(true);//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setAwaitTerminationSeconds(60);//用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        //执行初始化
        executor.initialize();
        return  executor;
    }

}
