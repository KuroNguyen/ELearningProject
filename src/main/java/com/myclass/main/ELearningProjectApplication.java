package com.myclass.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.myclass.entity")
public class ELearningProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningProjectApplication.class, args);
	}

}
