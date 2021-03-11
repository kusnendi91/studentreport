package com.kusnendi.studentreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudentreportApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentreportApplication.class, args);
	}

}
