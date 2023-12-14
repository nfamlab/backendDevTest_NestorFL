package com.backend.dev.similarproducts.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

/**
 * Webclient configuration.
 */
@Configuration
public class WebClientConfig {

	@Value("${mocks.request.timeout}")
	private Long timeout;

	@Value("${mocks.endpoint}")
	private String mockEndpoint;

	@Bean
	WebClient webClient(WebClient.Builder builder) {
		HttpClient client = HttpClient.create().responseTimeout(Duration.ofSeconds(timeout));

		return builder.baseUrl(mockEndpoint).clientConnector(new ReactorClientHttpConnector(client)).build();
	}

}