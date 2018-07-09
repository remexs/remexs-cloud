package com.remexs.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.remexs.**")
@EnableEurekaClient

public class AuthServerBootStrap {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerBootStrap.class, args);
	}
}
