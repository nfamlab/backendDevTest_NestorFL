package com.backend.dev.similarproducts.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Cache configuration.
 */
@Configuration
public class CacheConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

	@CacheEvict(value = "product", allEntries = true)
	@Scheduled(fixedRateString = "${similarproducts.product.cache.refresh}")
	public void cleanProductCache() {
		LOGGER.info("Cleaning Product cache.");
	}

}
