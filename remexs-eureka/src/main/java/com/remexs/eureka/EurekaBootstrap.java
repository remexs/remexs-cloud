package com.remexs.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description 服务注册中心
 * 
 * @author remex
 * @email bigdrone@163.com
 * @date 2017年11月6日 下午5:14:52
 */
@EnableEurekaServer // 启动一个服务注册中心提供给其他应用进行对话
@SpringBootApplication
public class EurekaBootstrap {
	public static void main(String[] args) {
		SpringApplication.run(EurekaBootstrap.class, args);
	}
}
