package com.remexs.corp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.remexs.**")
public class CorpBootStrap {

	public static void main(String[] args) {
		SpringApplication.run(CorpBootStrap.class, args);
	}
}
