package com.brixo.sytem.creditmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CreditmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditmanagementApplication.class, args);
	}

}
