package com.certus.ivma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient//表明自己是一个eurekaclient，服务提供者
//@RestController
@MapperScan("com.certus.ivma.mapper")
@EnableFeignClients//开启Feign的功能
@EnableHystrix//开启ribbon Hystrix
public class IvmaAppvideoMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IvmaAppvideoMonitorApplication.class, args);
	}
	@Bean
	@LoadBalanced
	//开启负载均衡的功能
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
