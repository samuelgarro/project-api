package com.unvime.projectapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.unvime.projectapi.repository")
public class ProjectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApiApplication.class, args);
	}

}
