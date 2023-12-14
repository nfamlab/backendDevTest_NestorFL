package com.backend.dev.similarproducts.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.backend.dev.similarproducts.exception.ProductException;
import com.backend.dev.similarproducts.exception.ProductNotFoundException;
import com.backend.dev.similarproducts.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Mock server requests.
 */
@Component
public class MockServerClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(MockServerClient.class);

	private final WebClient webClient;

	/**
	 * Default constructor
	 * 
	 * @param webClient
	 */
	@Autowired
	public MockServerClient(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	/**
	 * Contructor for test.
	 * 
	 * @param baseUrl Endpoint for WebClient.
	 */
	public MockServerClient(String baseUrl) {
		super();
		this.webClient = WebClient.create(baseUrl);
	}

	/**
	 * Get ids for similar products.
	 * 
	 * @param productId Product identifier.
	 * @return Flux<String> Identifiers for similar products.
	 */
	public Flux<String> getSimilarIds(String productId) {
		LOGGER.info("Obtaining ids of products similar to product with id {}.", productId);
		return webClient.get().uri("/product/{productId}/similarids", productId).retrieve()
				.onStatus(HttpStatus::isError, response -> {
					return handleError(response, productId);
				}).bodyToMono(new ParameterizedTypeReference<List<String>>() {
				}).flatMapMany(Flux::fromIterable);
	}

	/**
	 * Get detail for a product identified by its identifier. If an error occurs, it
	 * is retried a certain number of times. If the error continues, it is assumed
	 * that the product does not exist.
	 * 
	 * @param productId Product identifier.
	 * @return Mono<Product> Product detail.
	 */
	@Cacheable(value = "product", unless = "#result == null")
	public Mono<Product> getProductDetail(String productId) {
		LOGGER.info("Obtaining detail for product with id {}.", productId);
		return webClient.get().uri("/product/{productId}", productId).retrieve().bodyToMono(Product.class)
				.doOnError(ex -> LOGGER.error("Error obtaining detail for product with id {}.", productId))
				.onErrorComplete();
	}

	/**
	 * Handler for error from Mock server.
	 * 
	 * @param <T>
	 * @param response  Client response.
	 * @param productId Product identifier.
	 * @return Mono<T> Error.
	 */
	private <T> Mono<T> handleError(ClientResponse response, String productId) {
		HttpStatus status = response.statusCode();
		if (status.value() == HttpStatus.NOT_FOUND.value()) {
			return Mono.error(new ProductNotFoundException("Product not found", productId));
		} else {
			return Mono.error(new ProductException("Internal server error", productId));
		}
	}

}
