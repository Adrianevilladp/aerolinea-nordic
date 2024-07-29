package com.aerovik.nordic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class NordicApplication {

	public static void main(String[] args) {
		SpringApplication.run(NordicApplication.class, args);
	}

}
