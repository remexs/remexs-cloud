package com.remexs.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableZuulProxy
@ComponentScan("com.remexs.**")
@EnableEurekaClient
@EnableFeignClients({"com.remexs.auth.client.feign"})

public class GateBootStrap {
	public static void main(String[] args) {
		SpringApplication.run(GateBootStrap.class, args);
	}
}
