package com.certus.ivma.service.hystrix;

import com.certus.ivma.entity.RequestInfo;
import com.certus.ivma.entity.ReturnInfo;
import com.certus.ivma.service.CallServiceCrawl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 123 on 2018/11/2.
 */
//需要实现CallServiceCrawl接口，并注入到Ioc容器中
@Component
public class SchedualServiceCrawlHystric implements CallServiceCrawl {
    private static final Logger LOG = LoggerFactory.getLogger(SchedualServiceCrawlHystric.class);

    @Override
    public ReturnInfo sayCrawlFromClientOne(RequestInfo requestInfo) {
        LOG.info(">>> 分配任务失败... "+requestInfo.toString());
        ReturnInfo info = new ReturnInfo();
        info.setIsSuccess(false);
        info.setInfo("filed 连接服务失败，分配失败");
        return info;
    }
}
