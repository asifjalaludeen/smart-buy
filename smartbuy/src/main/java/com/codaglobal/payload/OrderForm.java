package com.codaglobal.payload;

import java.util.Date;
import java.util.List;

/**
 * @author Asif Jalaludeen
 *
 */
public class OrderForm {
	private Long id;
	private List<String> productNames;
	private String status;
	private Date orderDate;
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
	 * @return the productNames
	 */
	public List<String> getProductNames() {
		return productNames;
	}
	/**
	 * @param productNames the productNames to set
	 */
	public void setProductNames(List<String> productNames) {
		this.productNames = productNames;
	}
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
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
