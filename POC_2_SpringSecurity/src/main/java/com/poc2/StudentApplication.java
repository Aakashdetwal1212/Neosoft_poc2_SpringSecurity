package com.poc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(StudentApplication.class, args);

	}

}
