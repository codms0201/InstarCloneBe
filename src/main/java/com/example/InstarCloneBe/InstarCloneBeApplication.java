package com.example.InstarCloneBe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InstarCloneBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstarCloneBeApplication.class, args);
	}
}
