package com.backend.dev.similarproducts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * Mapper configuration.
 */
@Configuration
public class MapperConfig {

	@Bean
	@Primary
	ObjectMapper objectMapper() {
		return JsonMapper.builder().configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true).build();
	}

}