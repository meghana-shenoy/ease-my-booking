package com.example.easemybooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EasemybookingApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EasemybookingApplication.class, args);
		System.out.println("hi");
	}
}
