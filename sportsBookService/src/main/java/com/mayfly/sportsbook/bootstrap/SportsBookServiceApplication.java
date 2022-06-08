package com.mayfly.sportsbook.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.mayfly.sportsbook"})
public class SportsBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsBookServiceApplication.class, args);
	}

}
