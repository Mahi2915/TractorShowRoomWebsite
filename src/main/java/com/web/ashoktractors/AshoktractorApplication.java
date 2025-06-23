package com.web.ashoktractors;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.web.ashoktractors")
public class AshoktractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AshoktractorApplication.class, args);
		System.out.println("hi");
	}

}
