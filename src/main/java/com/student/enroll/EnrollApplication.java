package com.student.enroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EnrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollApplication.class, args);
	}

}
