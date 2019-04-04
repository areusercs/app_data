package com.certus.ivma.service.impl;

import com.certus.ivma.entity.AppVideoCrawlLog;
import com.certus.ivma.entity.AppVideoCrawlTask;
import com.certus.ivma.entity.TaskExecuteContext;
import com.certus.ivma.service.TaskExecuteService;
import com.certus.ivma.util.StringUtils;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.concurrent.Future;

/**
 * Created by 123 on 2019/3/6.
 */
@Service
public class TaskExecuteServiceImpl implements TaskExecuteService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskExecuteServiceImpl.class);

    @Async("taskExecute")//taskExecute即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    @Override
    public Future<AppVideoCrawlLog> executeAsync(TaskExecuteContext taskExecuteContext) {
        AppVideoCrawlTask crawlTask = taskExecuteContext.getCrawlTask();
        AppVideoCrawlLog crawlLog = taskExecuteContext.getCrawlLog();
        //执行此任务时，首先将这个任务的app的id 作为redis的key ，value为 0  不超时
        //TODO
//			RedisUtils.setString(crawlTask.getAppId().toString(),AppVideoCrawlConstants.APP_VIDEO_REDIS_KEY_TIMEOUT, "0");

        GroovyClassLoader loader = null;
        try {
            if(!StringUtils.isEmpty(crawlTask.getAppEngName())){
                String scriptUrlPath = taskExecuteContext.getGroovyScriptHttpPath() + (taskExecuteContext.getGroovyScriptHttpPath().endsWith("/") ? "" : "/") + crawlTask.getAppEngName() + ".groovy";
                LOG.info("执行APP视频节目抓取脚本：{}", scriptUrlPath);
                ClassLoader parent = this.getClass().getClassLoader();
                loader = new GroovyClassLoader(parent);
                //https://github.com/userarecs/CheSh/blob/master/shell/pipixia_1_5_8_android.groovy
                Class<?> gclass = loader.parseClass(DefaultGroovyMethods.getText(new URL(scriptUrlPath), "utf-8"));
                GroovyObject groovyObject = (GroovyObject) gclass.newInstance();
                groovyObject.invokeMethod("run", null);
                crawlLog.setSuccess(true);
                crawlLog.setMessage("OK");
            }else{
                crawlLog.setSuccess(false);
                crawlLog.setMessage("抓取脚本名称为空!");
            }
        } catch (Throwable e) {
            String message = "执行APP视频节目抓取脚本出错！(" + e.getMessage() + ")";
            LOG.error(message, e);
            crawlLog.setSuccess(false);
            crawlLog.setMessage(message);
        } finally {
            IOUtils.closeQuietly(loader);
//            System.gc();
            LOG.info("执行APP视频节目抓取脚本完毕》》》");
        }
        return new AsyncResult<AppVideoCrawlLog>(crawlLog);
    }

}
