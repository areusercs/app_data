package com.certus.ivma.service;

import com.certus.ivma.em.AppVideoCrawlTaskDispatchType;
import com.certus.ivma.entity.AppVideoCrawlTaskType;
import com.certus.ivma.entity.AppVideoCrawlLog;
import com.certus.ivma.entity.AppVideoCrawlTask;
import com.certus.ivma.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123 on 2019/2/21.
 */
@Service
public interface AppVideoCrawlTaskService {
    Users getAll();

    void batchCreateCrawlTask(String batchNo,AppVideoCrawlTaskType taskType, AppVideoCrawlTaskDispatchType dispatchTask);
    void batchCreateCrawlTask(String batchNo, com.certus.ivma.em.AppVideoCrawlTaskType taskType, AppVideoCrawlTaskDispatchType dispatchTask);


    void reScheduleFailFastCrawlTask(String batchNo, int runningTimeLimit);

    List<AppVideoCrawlTask> dispatchCrawlTask(String batchNo, AppVideoCrawlTaskType taskType);

    int updataCrawlTaskStatus(AppVideoCrawlTask appVideoCrawlTask);

    void updataCrawlTaskScheduleHostByTaskId(String ip, Long taskId);

    AppVideoCrawlLog beginTask(AppVideoCrawlTask appVideoCrawlTask,Integer poolCapacity);

    void endRunningTask(AppVideoCrawlTask crawlTask, AppVideoCrawlLog crawlLog);
}
