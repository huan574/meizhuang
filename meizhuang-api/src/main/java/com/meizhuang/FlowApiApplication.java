package com.meizhuang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//开启定时
@EnableScheduling
@SpringBootApplication
public class FlowApiApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(FlowApiApplication.class, args);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
