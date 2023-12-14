package com.backend.dev.similarproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SimilarProducts main application class.
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SimilarProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimilarProductsApplication.class, args);
	}

}
