package com.backend.dev.similarproducts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.backend.dev.similarproducts.client.MockServerClient;
import com.backend.dev.similarproducts.exception.ProductNotFoundException;
import com.backend.dev.similarproducts.model.Product;

import reactor.core.publisher.Flux;

/**
 * Service for Product.
 */
@Service
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	private MockServerClient mockServerClient;

	public ProductService(MockServerClient mockServerClient) {
		super();
		this.mockServerClient = mockServerClient;
	}

	/**
	 * Get products similar to another product identified by its identifier.
	 * 
	 * @param productId Product identifier.
	 * @return List<Product> List of similar products.
	 * @throws ProductNotFoundException When product does not exist.
	 */
	public Flux<Product> getSimilarProducts(String productId) throws ProductNotFoundException {
		LOGGER.info("Obtaining similar products for product with id {}.", productId);
		return mockServerClient.getSimilarIds(productId).flatMap(mockServerClient::getProductDetail);
	}

}
