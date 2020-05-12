package com.example.startsstock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StartsstockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartsstockApplication.class, args);
	}
}
