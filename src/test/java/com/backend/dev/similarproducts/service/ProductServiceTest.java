package com.backend.dev.similarproducts.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.dev.similarproducts.client.MockServerClient;
import com.backend.dev.similarproducts.exception.ProductNotFoundException;
import com.backend.dev.similarproducts.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Service for Product.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest
public class ProductServiceTest {

	private ProductService productService;

	private MockServerClient mockServerClientMockito;

	@BeforeEach
	void initialize() {
		mockServerClientMockito = Mockito.mock(MockServerClient.class);
		productService = new ProductService(mockServerClientMockito);
	}

	/**
	 * Test getSimilarProducts OK
	 */
	@Test
	void getSimilarProducts() {
		String[] ids = { "1", "2", "3" };
		Mockito.doReturn(Flux.just(ids)).when(mockServerClientMockito).getSimilarIds(Mockito.anyString());

		Product product1 = new Product("1", "Dress", new BigDecimal(19.99), true);
		Product product2 = new Product("2", "Blazer", new BigDecimal(29.99), false);
		Product product3 = new Product("3", "Boots", new BigDecimal(39.99), true);
		Mockito.doReturn(Mono.just(product1)).when(mockServerClientMockito).getProductDetail("1");
		Mockito.doReturn(Mono.just(product2)).when(mockServerClientMockito).getProductDetail("2");
		Mockito.doReturn(Mono.just(product3)).when(mockServerClientMockito).getProductDetail("3");

		Flux<Product> similarProducts = productService.getSimilarProducts("1");
		StepVerifier.create(similarProducts).expectNext(product1).expectNext(product2).expectNext(product3)
				.verifyComplete();
	}

}
