package com.certus.ivma.service;

import com.certus.ivma.entity.RequestInfo;
import com.certus.ivma.entity.ReturnInfo;
import com.certus.ivma.service.hystrix.SchedualServiceCrawlHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 123 on 2018/11/2.
 */
@FeignClient(value = "ivma-appvideo-crawl4",fallback = SchedualServiceCrawlHystric.class)
public interface CallServiceCrawl {

    /**
     *  调用ivma-appvideo-crawl服务 执行app脚本
     */
//    @RequestMapping(value = "/hi",method = RequestMethod.GET)
//    ReturnInfo sayCrawlFromClientOne(@RequestBody RequestInfo requestInfo);

    @RequestMapping(method = RequestMethod.POST, value = "/hi", consumes="application/json")
    ReturnInfo sayCrawlFromClientOne(RequestInfo requestInfo);
}
