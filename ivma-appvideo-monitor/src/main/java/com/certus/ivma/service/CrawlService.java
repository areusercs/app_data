package com.certus.ivma.service;

import com.certus.ivma.entity.RequestInfo;
import com.certus.ivma.entity.ReturnInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 123 on 2019/3/21.
 */
@Service
public class CrawlService {
    private static final Logger LOG = LoggerFactory.getLogger(CrawlService.class);

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "crawlErrorHystric")//对该方法创建了熔断器的功能，并指定了fallbackMethod熔断方法
    public ReturnInfo crawlService(RequestInfo requestInfo) {
        String url = "http://ivma-appvideo-crawl"+requestInfo.getAppVideoCrawlTask().getTaskType()+"/receiveMission";
        //设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        //
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);

        //javabean 转 json
        JSONObject object = JSONObject.fromObject(requestInfo);
        String requestJson  = object.toString();
        LOG.debug(">>>requestJson:"+requestJson);

        //使用post方法传递json串 success
//        HttpEntity<String> entity = new HttpEntity<String>(requestJson,httpHeaders);
//        ResponseEntity<ReturnInfo> resp = restTemplate.postForEntity(url, entity,ReturnInfo.class);
//        ReturnInfo returnInfo = resp.getBody();

        //使用 exchange 方法 success
        HttpEntity<String> requestEntity = new HttpEntity<String>(requestJson, httpHeaders);
        ResponseEntity<ReturnInfo> resp = restTemplate.exchange(url, HttpMethod.POST,requestEntity, ReturnInfo.class);
        ReturnInfo returnInfo = resp.getBody();

        return returnInfo;
    }

    public ReturnInfo crawlErrorHystric(RequestInfo requestInfo) {
        LOG.debug(">>> 分配任务失败... " + requestInfo.toString());
        ReturnInfo info = new ReturnInfo();
        info.setIsSuccess(false);
        info.setInfo("filed 连接服务失败，分配失败");
        return info;
    }
}
