package com.ibm.common.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ibm.common" })
public class CommonGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonGatewayApplication.class, args);
	}

}
