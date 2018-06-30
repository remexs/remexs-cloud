package com.remexs.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.remexs.**")
@EnableEurekaClient
public class CompanyBootStrap {

	public static void main(String[] args) {
		SpringApplication.run(CompanyBootStrap.class, args);
	}
}