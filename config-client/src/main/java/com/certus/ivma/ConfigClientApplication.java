package com.certus.ivma;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

//	@Value("${spring.rabbitmq.port}")
//	String foo;
	@Value("${test.hello}")
	String foo;

	@RequestMapping(value = "/h")
	public String hi(){
		return foo;
	}
}
