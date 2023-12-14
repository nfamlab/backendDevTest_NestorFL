package com.backend.dev.similarproducts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for product not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	/**
	 * Generated serial version ID.
	 */
	private static final long serialVersionUID = 5461534493341407185L;

	private final String productId;

	public ProductNotFoundException(String message, String productId) {
		super(message);
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}

}
