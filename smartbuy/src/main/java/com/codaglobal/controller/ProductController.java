package com.codaglobal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codaglobal.payload.ProductForm;
import com.codaglobal.service.ProductService;

/**
 * @author Asif Jalaludeen
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/register")
	@PreAuthorize("hasAuthority('SELLER')")
	public ResponseEntity<?> loadProduct(@Valid @RequestBody ProductForm productForm){
		return productService.loadProducts(productForm);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('SELLER') or hasAuthority('BUYER')")
	public ResponseEntity<?> getProducts(@RequestParam(value = "findBy", required = false) String findBy,
			@RequestParam(value = "category", required = false) String category){
		return productService.getProducts(findBy, category);
	}
}
