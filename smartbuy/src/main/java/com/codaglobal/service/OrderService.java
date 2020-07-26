package com.codaglobal.service;

import org.springframework.http.ResponseEntity;

import com.codaglobal.payload.OrderRequest;

/**
 * @author Asif Jalaludeen
 *
 */
public interface OrderService {
	public ResponseEntity<?> getOrderByUser();
	
	public ResponseEntity<?> getPendingOrders();
	
	public ResponseEntity<?> placeOrder(OrderRequest orderRequest);
	
	public ResponseEntity<?> dispatchOrder(Long id);
	
	public ResponseEntity<?> receiveOrder(Long id);
}
