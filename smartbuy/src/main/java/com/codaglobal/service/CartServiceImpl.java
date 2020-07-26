package com.codaglobal.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.codaglobal.infrastructure.Log;
import com.codaglobal.model.Cart;
import com.codaglobal.model.Product;
import com.codaglobal.model.User;
import com.codaglobal.payload.CartForm;
import com.codaglobal.payload.ProductForm;
import com.codaglobal.repository.CartRepository;
import com.codaglobal.repository.ProductRepository;
import com.codaglobal.repository.UserRepository;
import com.codaglobal.util.MessageResponse;
import com.codaglobal.util.UserDetailsImpl;

/**
 * @author Asif Jalaludeen
 *
 */
@Service
public class CartServiceImpl implements CartService{
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
	public ResponseEntity<?> getItemsOnCart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ResponseEntity<?> addToCart(Long productId) {
		ResponseEntity<?> response;
		try {
			Product product = productRepository.findById(productId).get();
			Cart cart = new Cart();
			cart.setProduct(product);
			UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userRepository.findByUsername(userDetails.getUsername()).get();
			logger.info("Adding product to cart for user={} product={}", user.getUsername(), product.getName());
			cart.setUser(user);
			cartRepository.save(cart);
			response = ResponseEntity.ok(new MessageResponse("Product added to cart successfully"));
		} catch(Exception e) {
			logger.error("Adding product to cart failed");
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}

	@Override
	@Transactional
	public ResponseEntity<?> removeFromCart(Long cartId) {
		ResponseEntity<?> response;
		try {
			logger.info("Removing product from cart");
			cartRepository.deleteById(cartId);
			response = ResponseEntity.ok(new MessageResponse("Product removed from cart successfully"));
		} catch(Exception e) {
			logger.error("Removing product from cart failed");
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseEntity<?> getCartItems() {
		ResponseEntity<?> response;
		try {
			UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userRepository.findByUsername(userDetails.getUsername()).get();
			logger.info("Gettings item on cart for user={}", user.getUsername());
			List<Cart> cartItems = cartRepository.findByUser(user);
			response = ResponseEntity.ok(modeltoForm(cartItems));
		} catch(Exception e) {
			logger.error("Removing product from cart failed");
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}
	
	private List<CartForm> modeltoForm(List<Cart> cartItems) {
		return cartItems.stream().map(item -> {
			ProductForm product = new ProductForm();
			product.setId(item.getProduct().getId());
			product.setName(item.getProduct().getName());
			product.setCategory(item.getProduct().getCategory().name());
			product.setPrice(item.getProduct().getPrice());
			product.setQuantity(item.getProduct().getStock());
			
			CartForm cartForm = new CartForm();
			cartForm.setId(item.getId());
			cartForm.setProduct(product);
			
			return cartForm;
		}).collect(Collectors.toList());
	}

}
