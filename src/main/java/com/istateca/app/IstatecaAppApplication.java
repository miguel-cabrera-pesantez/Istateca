package com.istateca.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IstatecaAppApplication {

	public static void main(String[] args) {
		System.out.println("TESTING 2 <<<<<------------------");
		SpringApplication.run(IstatecaAppApplication.class, args);
	}

}
