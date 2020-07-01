package com.example.userSBN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*Die Annotation @SpringBootApplication ist eine Sammel-Annotation, die @Configuration, @EnableAutoConfiguration und @ComponentScan bündelt.*/
@SpringBootApplication
public class UserSbnApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSbnApplication.class, args);
	}

}
