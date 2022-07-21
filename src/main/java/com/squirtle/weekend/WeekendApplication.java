package com.squirtle.weekend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WeekendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeekendApplication.class, args);
	}

}
