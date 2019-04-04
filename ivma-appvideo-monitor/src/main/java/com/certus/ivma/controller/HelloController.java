package com.certus.ivma.controller;

import com.certus.ivma.service.AppVideoCrawlTaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by 123 on 2019/2/21.
 */
@Controller
@RequestMapping("/freemarker")
public class HelloController {

    @Value("${server.port}")
    String port;
    @Resource
    private AppVideoCrawlTaskService service;

    @RequestMapping("/hello")
    public String home(@RequestParam(value = "id", defaultValue = "1") Long id) {
        System.out.println(id+port);
        System.out.println( service.getAll());
        return "index";
    }
//    @RequestMapping("/hello")
//    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
//        return "hi " + name + " ,i am from port:" + port;
//    }
}
