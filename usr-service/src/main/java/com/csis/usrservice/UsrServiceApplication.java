package com.csis.usrservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsrServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsrServiceApplication.class, args);
	}

}
