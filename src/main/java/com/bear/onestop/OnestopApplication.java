package com.bear.onestop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class OnestopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnestopApplication.class, args);
	}

}
