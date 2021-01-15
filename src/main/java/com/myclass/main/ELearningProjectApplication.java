package com.myclass.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.myclass.entity")
@ComponentScan("com.myclass")
public class ELearningProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningProjectApplication.class, args);
	}

}
