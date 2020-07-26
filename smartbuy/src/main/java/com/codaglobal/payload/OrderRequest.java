package com.codaglobal.payload;

import java.util.Date;
import java.util.List;

/**
 * @author Asif Jalaludeen
 *
 */
public class OrderRequest {
	private Long id;
	private List<CartForm> cartItems;
	private String status;
	private Date createdDate;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	 * @return the cartItems
	 */
	public List<CartForm> getCartItems() {
		return cartItems;
	}
	/**
	 * @param cartItems the cartItems to set
	 */
	public void setCartItems(List<CartForm> cartItems) {
		this.cartItems = cartItems;
	}
}
