package com.tech.agroanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AgroAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgroAnalysisApplication.class, args);
	}
}
