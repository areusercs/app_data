package com.certus.ivma.service;

import com.certus.ivma.entity.TaskExecuteContext;
import org.springframework.stereotype.Service;

/**
 * Created by 123 on 2019/3/6.
 */
@Service
public interface AsyncService {
    /**
     * 执行异步任务
     */
    void executeAsync(TaskExecuteContext requestInfo);
}
