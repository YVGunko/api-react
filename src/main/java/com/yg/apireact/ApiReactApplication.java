package com.yg.apireact;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ApiReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiReactApplication.class, args);
	}
}
