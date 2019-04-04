package com.certus.ivma.service;

import com.certus.ivma.entity.AppVideoCrawlLog;
import com.certus.ivma.entity.TaskExecuteContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by 123 on 2019/3/6.
 */
@Service
public interface TaskExecuteService {
    /**
     * 执行异步任务
     */
    Future<AppVideoCrawlLog> executeAsync(TaskExecuteContext requestInfo);
}
