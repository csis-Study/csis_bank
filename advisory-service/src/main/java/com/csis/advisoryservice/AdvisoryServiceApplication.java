package com.csis.advisoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AdvisoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvisoryServiceApplication.class, args);
	}

}
