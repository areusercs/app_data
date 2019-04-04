package com.certus.ivma.service.impl;

import com.certus.ivma.em.AppVideoCrawlTaskDispatchType;
import com.certus.ivma.em.AppVideoCrawlTaskStatus;
import com.certus.ivma.em.AppVideoCrawlTaskType;
import com.certus.ivma.entity.*;
import com.certus.ivma.exception.BusinessException;
import com.certus.ivma.mapper.*;
import com.certus.ivma.service.AppVideoCrawlTaskService;
import com.certus.ivma.util.CollectionUtils;
import com.certus.ivma.util.DateTimeUtils;
import com.certus.ivma.util.HostUtil;
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
public class AppVideoCrawlTaskServiceImpl implements AppVideoCrawlTaskService {

    private static final Logger LOG = LoggerFactory.getLogger(AppVideoCrawlTaskServiceImpl.class);

    @Autowired
    private UserMapper dao;
    @Autowired
    private AppInfoMapper appInfoMapper;
    @Autowired
    private AppVideoCrawlTaskMapper appVideoCrawlTaskMapper;
    @Autowired
    private AppVideoCrawlHostMapper appVideoCrawlHostMapper;
    @Autowired
    private AppVideoCrawlLogMapper appVideoCrawlLogMapper;
    @Override
    public Users getAll() {
        return dao.getAll();
    }

    @Override
    public void batchCreateCrawlTask(String batchNo, com.certus.ivma.entity.AppVideoCrawlTaskType taskType, AppVideoCrawlTaskDispatchType dispatchTask) {
        //TODO 创建任务
        System.out.println("创建任务ing...."+taskType.getAppCrawlTypeName());

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
    public List<AppVideoCrawlTask> dispatchCrawlTask(String batchNo, com.certus.ivma.entity.AppVideoCrawlTaskType taskType) {
        LOG.info("");
        //查询是否存在符合批次和抓取类型的任务
//        int taskCount = appVideoCrawlTaskMapper.countCrawlTaskSchedule(batchNo, taskType.getTypeCode());
        List<AppVideoCrawlTask> appVideoCrawlTaskList = appVideoCrawlTaskMapper.selectCrawlTaskSchedule(batchNo, taskType.getAppCrawlTypeCode());
        LOG.info(taskType.getAppCrawlTypeName()+"任务 待分配任务数量taskCount:"+appVideoCrawlTaskList.size());
        return appVideoCrawlTaskList;
    }

    @Override
    public int updataCrawlTaskStatus(AppVideoCrawlTask appVideoCrawlTask) {
        int updateLine = appVideoCrawlTaskMapper.updateCrawlTask(appVideoCrawlTask);
        return updateLine;
    }

    @Override
    public void updataCrawlTaskScheduleHostByTaskId(String ip, Long taskId) {
        appVideoCrawlTaskMapper.updataCrawlTaskScheduleHostByTaskId(ip,taskId);
    }

    @Override
    @Transactional
    public AppVideoCrawlLog beginTask(AppVideoCrawlTask appVideoCrawlTask,Integer poolCapacity) {
        String nowTime = DateTimeUtils.formatNow();
        appVideoCrawlTask.setRunningTime(nowTime);
        appVideoCrawlTask.setScheduleHost(HostUtil.getLocalHostIp());
        appVideoCrawlTask.setStatus(AppVideoCrawlTaskStatus.TASK_RUNNING.getStatusCode());//运行中 1->2
        int updateLine = appVideoCrawlTaskMapper.updateCrawlTask(appVideoCrawlTask);
        if(updateLine==1) {
            //修改HOST表NOW_POLL_SIZE容量使用量
            String hostIp = HostUtil.getLocalHostIp();
            appVideoCrawlHostMapper.updateKeepAliveTime(hostIp,poolCapacity);
            //添加日志表数据
            AppVideoCrawlLog crawlLog = new AppVideoCrawlLog();
            crawlLog.setTaskId(appVideoCrawlTask.getTaskId());
            crawlLog.setTaskStartTime(nowTime);
            crawlLog.setServerIp(HostUtil.getLocalHostIp());
            crawlLog.setInsertTime(DateTimeUtils.formatNow());
            crawlLog.setAppId(appVideoCrawlTask.getAppId());
            crawlLog.setTaskBatchNo(appVideoCrawlTask.getBatchNo());
            int insertLine = appVideoCrawlLogMapper.insertCrawlLog(crawlLog);
            if(insertLine!=1){
                throw new BusinessException("创建任务Log数据异常");
            }else {
                return crawlLog;
            }
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
    public void endRunningTask(AppVideoCrawlTask crawlTask, AppVideoCrawlLog crawlLog) {
        String nowTime = DateTimeUtils.formatNow();
        if(crawlTask != null){
            crawlTask.setStatus(AppVideoCrawlTaskStatus.TASK_FINISHED.getStatusCode());
            crawlTask.setFinishedTime(nowTime);
            appVideoCrawlTaskMapper.updateCrawlTask(crawlTask);
        }
        if(crawlLog != null){
            crawlLog.setTaskEndTime(nowTime);
            appVideoCrawlLogMapper.updateCrawlLog(crawlLog);
        }
    }


}
