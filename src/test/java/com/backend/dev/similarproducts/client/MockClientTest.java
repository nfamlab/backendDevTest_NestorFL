package com.backend.dev.similarproducts.client;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.dev.similarproducts.exception.ProductException;
import com.backend.dev.similarproducts.exception.ProductNotFoundException;
import com.backend.dev.similarproducts.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Service for Product.
 */
@SpringBootTest
public class MockClientTest {

	public static MockWebServer mockBackEnd;

	private MockServerClient mockServerClient;

	private static ObjectMapper mapper;

	@BeforeAll
	static void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();
		mapper = new ObjectMapper();
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
		mapper = null;
	}

	@BeforeEach
	void initialize() {
		String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
		mockServerClient = new MockServerClient(baseUrl);
	}

	/**
	 * Test getSimilarIds OK
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	void getSimilarIds() throws JsonProcessingException {
		String[] ids = { "1", "2", "3" };
		mockBackEnd.enqueue(new MockResponse().setBody(mapper.writeValueAsString(ids)).addHeader("Content-Type",
				"application/json"));

		Flux<String> similarIds = mockServerClient.getSimilarIds("1");

		StepVerifier.create(similarIds).expectNext(ids[0]).expectNext(ids[1]).expectNext(ids[2]).verifyComplete();
	}

	/**
	 * Test getSimilarIds NOT_FOUND
	 */
	@Test
	void getSimilarIdsNotFound() {
		mockBackEnd.enqueue(
				new MockResponse().setResponseCode(404).setBody("{}").addHeader("Content-Type", "application/json"));

		Flux<String> similarIds = mockServerClient.getSimilarIds("1");

		StepVerifier.create(similarIds).expectError(ProductNotFoundException.class).verify();
	}

	/**
	 * Test getSimilarIds ERROR
	 */
	@Test
	void getSimilarIdsError() {
		mockBackEnd.enqueue(
				new MockResponse().setResponseCode(500).setBody("{}").addHeader("Content-Type", "application/json"));

		Flux<String> similarIds = mockServerClient.getSimilarIds("1");

		StepVerifier.create(similarIds).expectError(ProductException.class).verify();
	}

	/**
	 * Test getProductDetail OK
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	void getProductDetail() throws JsonProcessingException {
		Product product = new Product("2", "Dress", new BigDecimal(19.99), true);
		mockBackEnd.enqueue(new MockResponse().setBody(mapper.writeValueAsString(product)).addHeader("Content-Type",
				"application/json"));

		Mono<Product> productResponse = mockServerClient.getProductDetail(product.getId());

		StepVerifier.create(productResponse).expectNextMatches(response -> response.getId().equals(product.getId()))
				.verifyComplete();
	}

	/**
	 * Test getProductDetail ERROR
	 */
	@Test
	void getProductDetailError() {
		mockBackEnd.enqueue(
				new MockResponse().setResponseCode(500).setBody("{}").addHeader("Content-Type", "application/json"));

		Mono<Product> productResponse = mockServerClient.getProductDetail("6");

		StepVerifier.create(productResponse).verifyComplete();
	}

}
