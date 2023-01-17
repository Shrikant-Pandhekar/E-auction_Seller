package com.auction.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableWebMvc
@EnableSwagger2
public class SellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerApplication.class, args);
	}

}
