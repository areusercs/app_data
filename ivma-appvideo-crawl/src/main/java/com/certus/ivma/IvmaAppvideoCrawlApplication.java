package com.certus.ivma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//表明自己是一个eurekaclient，服务提供者
public class IvmaAppvideoCrawlApplication {

	public static void main(String[] args) {

		SpringApplication.run(IvmaAppvideoCrawlApplication.class, args);
	}

}
