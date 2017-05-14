package com.rachel.cider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rachel.cider")
public class CiderClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiderClientApplication.class, args);
	}
}
