package com.codaglobal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codaglobal.service.CartService;

/**
 * @author Asif Jalaludeen
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	CartService cartService;
	
	@PostMapping("/add/{product_id}")
	@PreAuthorize("hasAuthority('BUYER')")
	public ResponseEntity<?> addToCart(@PathVariable("product_id") Long productId){
		return cartService.addToCart(productId);
	}
	
	@DeleteMapping("/remove/{cart_id}")
	@PreAuthorize("hasAuthority('BUYER')")
	public ResponseEntity<?> removeFromCart(@PathVariable("cart_id") Long cartId){
		return cartService.removeFromCart(cartId);
	}
	
	@GetMapping()
	@PreAuthorize("hasAuthority('BUYER')")
	public ResponseEntity<?> getCartItems(){
		return cartService.getCartItems();
	}
}
