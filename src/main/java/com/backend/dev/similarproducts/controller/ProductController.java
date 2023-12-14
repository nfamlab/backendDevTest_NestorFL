package com.backend.dev.similarproducts.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dev.similarproducts.model.Product;
import com.backend.dev.similarproducts.service.ProductService;

import reactor.core.publisher.Flux;

/**
 * Controller for Product.
 */
@RestController
@RequestMapping("/product/")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	/**
	 * Get products similar to another product identified by its identifier.
	 * 
	 * @param productId Product identifier.
	 * @return Flux<Product> List of similar products.
	 */
	@GetMapping(value = "{productId}/similar", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Product> getSimilarProducts(@PathVariable String productId) {
		return productService.getSimilarProducts(productId);
	}

}
