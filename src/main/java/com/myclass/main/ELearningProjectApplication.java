package com.myclass.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;

import com.myclass.core.SpringSecurityAuditorAware;
import com.myclass.entity.User;

@SpringBootApplication
@EntityScan("com.myclass.entity")
@EnableJpaRepositories("com.myclass.repository")
@ComponentScan("com.myclass")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ELearningProjectApplication {
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new SpringSecurityAuditorAware();
	}

	public static void main(String[] args) {
		SpringApplication.run(ELearningProjectApplication.class, args);
	}

}
