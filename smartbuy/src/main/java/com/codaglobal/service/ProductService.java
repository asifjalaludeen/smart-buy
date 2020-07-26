package com.codaglobal.service;

import org.springframework.http.ResponseEntity;

import com.codaglobal.payload.ProductForm;

/**
 * @author Asif Jalaludeen
 *
 */
public interface ProductService {
	public ResponseEntity<?> loadProducts(ProductForm productForm);
	
	public ResponseEntity<?> getProducts(String findBy, String category);
}
