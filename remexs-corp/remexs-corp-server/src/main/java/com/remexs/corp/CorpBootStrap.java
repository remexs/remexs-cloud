package com.remexs.corp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.remexs.**")
@EnableEurekaClient
@EnableFeignClients({"com.remexs.auth.client.feign"})
public class CorpBootStrap {

	public static void main(String[] args) {
		SpringApplication.run(CorpBootStrap.class, args);
	}
}
