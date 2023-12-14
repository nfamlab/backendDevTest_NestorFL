package com.backend.dev.similarproducts.model;

import java.math.BigDecimal;

/**
 * Model for Product entity.
 */
public class Product {
	private String id;
	private String name;
	private BigDecimal price;
	private Boolean availability;

	/**
	 * Default constructor.
	 */
	public Product() {
		super();
	}

	/**
	 * Constructor with all attributes.
	 * 
	 * @param id           Product identifier.
	 * @param name         Product name.
	 * @param price        Precio del producto.
	 * @param availability Product availability.
	 */
	public Product(String id, String name, BigDecimal price, Boolean availability) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.availability = availability;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

}
