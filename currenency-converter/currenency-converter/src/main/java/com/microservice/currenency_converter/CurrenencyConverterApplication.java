package com.microservice.currenency_converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrenencyConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrenencyConverterApplication.class, args);
	}

}
