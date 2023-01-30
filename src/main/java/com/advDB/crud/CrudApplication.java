package com.advDB.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */
@SpringBootApplication
@EnableMongoRepositories
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

}
