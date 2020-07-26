package com.codaglobal.service;

import org.springframework.http.ResponseEntity;

/**
 * @author Asif Jalaludeen
 *
 */
public interface CartService {
	public ResponseEntity<?> getItemsOnCart();
	
	public ResponseEntity<?> addToCart(Long productId);
	
	public ResponseEntity<?> removeFromCart(Long cartId);
	
	public ResponseEntity<?> getCartItems();
}
