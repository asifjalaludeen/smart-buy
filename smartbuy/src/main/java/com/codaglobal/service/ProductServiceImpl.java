package com.codaglobal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codaglobal.infrastructure.Log;
import com.codaglobal.model.Cart;
import com.codaglobal.model.Product;
import com.codaglobal.model.User;
import com.codaglobal.payload.ProductForm;
import com.codaglobal.repository.CartRepository;
import com.codaglobal.repository.ProductRepository;
import com.codaglobal.repository.UserRepository;
import com.codaglobal.util.MessageResponse;
import com.codaglobal.util.ProductCategory;
import com.codaglobal.util.UserDetailsImpl;

/**
 * @author Asif Jalaludeen
 *
 */
@Service
public class ProductServiceImpl implements ProductService{
	@Log
	Logger logger;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	
	@Override
	@Transactional
	public ResponseEntity<?> loadProducts(ProductForm productForm) {
		ResponseEntity<?> response;
		try {
			logger.info("Adding product to inventory productname={}", productForm.getName());
			UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userRepository.findByUsername(userDetails.getUsername()).get();
			Product product = new Product(productForm.getName(), ProductCategory.valueOf(productForm.getCategory()), 
					productForm.getPrice(), productForm.getQuantity(), user);
			productRepository.save(product);
			logger.info("product added to inventory productname={}", productForm.getName());
			response = ResponseEntity.ok(new MessageResponse("Product loaded successfully!"));
		} catch(Exception e) {
			logger.error("Loading product to inventory failed with productname={}", productForm.getName());
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseEntity<?> getProducts(String findBy, String category) {
		ResponseEntity<?> response;
		try {
			UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userRepository.findByUsername(userDetails.getUsername()).get();
			List<Product> products;
			if(findBy != null && !findBy.isEmpty()) {
				if("category".equalsIgnoreCase(findBy) && category != null && !category.isEmpty()) {
					logger.info("Fetching all products by category={}", category);
					products = productRepository.findByCategory(ProductCategory.valueOf(category));
				} else if("user".equalsIgnoreCase(findBy)) {
					
					logger.info("Fetching all products by user={}", user.getUsername());
					products = productRepository.findByUser(user);
				} else {
					logger.info("Fetching all products");
					products = productRepository.findAll();
				}
			} else {
				logger.info("Fetching all products");
				products = productRepository.findAll();
			}
			response = ResponseEntity.ok(modelToForm(products, user));
		} catch(Exception e) {
			logger.error("Failed to fetch all products", e.getMessage());
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}
	
	public List<ProductForm> modelToForm(List<Product> products, User user){
		return products.stream().map(product -> {
				ProductForm productForm = new ProductForm();
				productForm.setId(product.getId());
				productForm.setName(product.getName());
				productForm.setCategory(product.getCategory().name());
				productForm.setQuantity(product.getStock());
				productForm.setPrice(product.getPrice());
				List<Cart> cartItems = cartRepository.findByUserAndProduct(user, product);
				productForm.setCart(cartItems.size() > 0 ? true : false);
				return productForm;
			}).collect(Collectors.toList());
	}
}
