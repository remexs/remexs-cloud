package com.remexs.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@EnableZuulProxy
@SpringBootApplication

public class GateWayBootStrap {

	public static void main(String[] args) {
		SpringApplication.run(GateWayBootStrap.class, args);
	}
}
