package com.codaglobal.payload;

/**
 * @author Asif Jalaludeen
 *
 */
public class CartForm {
	private Long id;
	private ProductForm product;
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
	 * @return the product
	 */
	public ProductForm getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductForm product) {
		this.product = product;
	}
	
	
}
