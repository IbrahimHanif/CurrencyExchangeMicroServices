package com.microservice.currencyregistryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CurrencyRegistryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRegistryServiceApplication.class, args);
		System.out.println("Test1");
	}

}
