package com.example.startadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StartAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartAdminApplication.class, args);
	}
}
