package com.backend.dev.similarproducts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.dev.similarproducts.exception.ProductNotFoundException;
import com.backend.dev.similarproducts.exception.ProductException;

/**
 * Manage controller's exceptions
 */
@RestControllerAdvice
public class RestExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

	/**
	 * Product not found.
	 * 
	 * @param e ProductNotFoundException.
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e) {
		LOGGER.info("Product not found. Product Id: {}.", e.getProductId());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	/**
	 * Error with product.
	 * 
	 * @param e ProductException.
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<Object> handleProductException(ProductException e) {
		LOGGER.error("Server error: {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * Default handler for exceptions.
	 * 
	 * @param e Exception.
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception e) {
		LOGGER.error("Internal server error: {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
