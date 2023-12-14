package com.backend.dev.similarproducts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for product error.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductException extends RuntimeException {
	/**
	 * Generated serial version ID.
	 */
	private static final long serialVersionUID = -5614926541326417490L;

	private final String productId;

	public ProductException(String message, String productId) {
		super(message);
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}

}
