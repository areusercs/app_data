package com.certus.ivma.service.impl;

import com.certus.ivma.em.AppVideoCrawlTaskDispatchType;
import com.certus.ivma.em.AppVideoCrawlTaskStatus;
import com.certus.ivma.em.AppVideoCrawlTaskType;
import com.certus.ivma.entity.AppInfo;
import com.certus.ivma.entity.AppVideoCrawlTask;
import com.certus.ivma.entity.Users;
import com.certus.ivma.mapper.AppInfoMapper;
import com.certus.ivma.mapper.AppVideoCrawlHostMapper;
import com.certus.ivma.mapper.AppVideoCrawlTaskMapper;
import com.certus.ivma.mapper.UserMapper;
import com.certus.ivma.service.AppVideoCrawlLogService;
import com.certus.ivma.util.CollectionUtils;
import com.certus.ivma.util.DateTimeUtils;
import com.certus.ivma.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 123 on 2019/2/21.
 */
@Service
public class AppVideoCrawlLogServiceImpl implements AppVideoCrawlLogService {

    private static final Logger LOG = LoggerFactory.getLogger(AppVideoCrawlLogServiceImpl.class);

    @Autowired
    private UserMapper dao;
    @Autowired
    private AppInfoMapper appInfoMapper;
    @Autowired
    private AppVideoCrawlTaskMapper appVideoCrawlTaskMapper;
    @Autowired
    private AppVideoCrawlHostMapper appVideoCrawlHostMapper;
    @Override
    public Users getAll() {
        return dao.getAll();
    }

    @Transactional(rollbackFor=Exception.class, propagation= Propagation.REQUIRED)
    @Override
    public void batchCreateCrawlTask(String batchNo, AppVideoCrawlTaskType taskType, AppVideoCrawlTaskDispatchType dispatchTask) {
        //查询IVMA_APP表中所有可用app
        List<AppInfo> allAppInfoList = appInfoMapper.selectAllAppInfoList();
        if(!CollectionUtils.isEmpty(allAppInfoList)) {
            String nowTime = DateTimeUtils.formatNow();
            for (AppInfo appInfo : allAppInfoList) {
                boolean b = false;
                if(AppVideoCrawlTaskType.EMPHASIS_TASK.equals(taskType)){//重点任务
                    b = "1".equals(appInfo.getAppClassify());
                }else if(AppVideoCrawlTaskType.NOT_EMPHASIS_TASK.equals(taskType)){//非重点任务
                    b = "2".equals(appInfo.getAppClassify());
                }else if(AppVideoCrawlTaskType.GUANGDIAN_APP_TASK.equals(taskType)){//关于广电app的任务
                    b = "3".equals(appInfo.getAppClassify());
                }else if(AppVideoCrawlTaskType.DOUYIN_APP_TASK.equals(taskType)){//抖音app的任务
                    b = "4".equals(appInfo.getAppClassify());
                }
                if(b){
                    AppVideoCrawlTask task = new AppVideoCrawlTask();
                    task.setAppId(appInfo.getId());
                    task.setAppName(appInfo.getName());
                    task.setAppEngName(appInfo.getEngName());
                    task.setTaskType(taskType.getTypeCode());
                    task.setBatchNo(batchNo);
                    task.setStatus(AppVideoCrawlTaskStatus.TASK_WAIT_SCHEDULING.getStatusCode());
                    task.setCreateTime(nowTime);
                    task.setDispatchType(dispatchTask.getTypeCode());
                    Integer priority = 2;
                    if(!StringUtils.isEmpty(appInfo.getAppClassify())){
                        try {
                            priority = Integer.valueOf(appInfo.getAppClassify());
                        } catch (Exception e) {
                        }
                    }
                    task.setPriority(priority);
                    appVideoCrawlTaskMapper.insertCrawlTask(task);
                }


            }
        }
    }
    @Transactional(rollbackFor=Exception.class, propagation= Propagation.REQUIRED)
    @Override
    public void reScheduleFailFastCrawlTask(String batchNo, int runningTimeLimit) {
        List<AppVideoCrawlTask> failFastCrawlTaskList =  appVideoCrawlTaskMapper.selectFailFastCrawlTaskList(batchNo, runningTimeLimit);
        if(!CollectionUtils.isEmpty(failFastCrawlTaskList)){
            for(AppVideoCrawlTask crawlTask : failFastCrawlTaskList){
                appVideoCrawlTaskMapper.reCrawlTask(crawlTask.getTaskId(),crawlTask.getPriority());
            }
        }
    }

    @Transactional(rollbackFor=Exception.class, propagation= Propagation.REQUIRED)
    @Override
    public List<AppVideoCrawlTask> dispatchCrawlTask(String batchNo, AppVideoCrawlTaskType taskType) {
        LOG.info("");
        //查询是否存在符合批次和抓取类型的任务
//        int taskCount = appVideoCrawlTaskMapper.countCrawlTaskSchedule(batchNo, taskType.getTypeCode());
        List<AppVideoCrawlTask> appVideoCrawlTaskList = appVideoCrawlTaskMapper.selectCrawlTaskSchedule(batchNo, taskType.getTypeCode());
        LOG.info(taskType.getTypeName()+"任务 待分配任务数量taskCount:"+appVideoCrawlTaskList.size());
        return appVideoCrawlTaskList;
    }

    @Override
    public void updataCrawlTaskStatus(AppVideoCrawlTask appVideoCrawlTask) {
        appVideoCrawlTaskMapper.updateCrawlTask(appVideoCrawlTask);

    }

    @Override
    public void updataCrawlTaskScheduleHostByTaskId(String ip, Long taskId) {
        appVideoCrawlTaskMapper.updataCrawlTaskScheduleHostByTaskId(ip,taskId);
    }
/*
    @Transactional(rollbackFor=Exception.class, propagation= Propagation.REQUIRED)
    @Override
    public AppVideoCrawlHost dispatchCrawlTask(String batchNo, AppVideoCrawlTaskType taskType) {
        LOG.info("");
        //查询是否存在符合批次和抓取类型的任务
//        int taskCount = appVideoCrawlTaskMapper.countCrawlTaskSchedule(batchNo, taskType.getTypeCode());
        List<AppVideoCrawlTask> appVideoCrawlTaskList = appVideoCrawlTaskMapper.selectCrawlTaskSchedule(batchNo, taskType.getTypeCode());
        LOG.info(taskType.getTypeName()+"任务 待分配任务数量taskCount:"+appVideoCrawlTaskList.size());
        if(appVideoCrawlTaskList.size() > 0){
            for (AppVideoCrawlTask appVideoCrawlTask : appVideoCrawlTaskList) {
                //查询可分配任务机器  查询非活动主机 and keepalive_time最小的
                AppVideoCrawlHost appVideoCrawlHost = appVideoCrawlHostMapper.selectNotActiveCrawlTaskHost(taskType.getTypeCode());
                if(appVideoCrawlHost!=null){
                    String hostIp = appVideoCrawlHost.getHostIp();
                    String appEngName = appVideoCrawlTask.getAppEngName();
                    //将任务分配给这个ip的抓取服务//TODO
                }

            }
        }
        return null;
}*/
}
