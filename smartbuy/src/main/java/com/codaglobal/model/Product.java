package com.codaglobal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.codaglobal.util.ProductCategory;

/**
 * @author Asif Jalaludeen
 *
 */
@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="category")
	@Enumerated(EnumType.STRING)
	private ProductCategory category;
	@Column(name="price")
	private Long price;
	@Column(name="stock")
	private Long stock;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Product() {};
	
	/**
	 * @param name
	 * @param category
	 * @param price
	 * @param stock
	 * @param user
	 */
	public Product(String name, ProductCategory category, Long price, Long stock, User user) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.user = user;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the category
	 */
	public ProductCategory getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}
	/**
	 * @return the stock
	 */
	public Long getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(Long stock) {
		this.stock = stock;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
