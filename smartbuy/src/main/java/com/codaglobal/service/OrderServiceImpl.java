package com.codaglobal.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
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
import com.codaglobal.model.Order;
import com.codaglobal.model.Product;
import com.codaglobal.model.User;
import com.codaglobal.payload.OrderForm;
import com.codaglobal.payload.OrderRequest;
import com.codaglobal.repository.CartRepository;
import com.codaglobal.repository.OrderRepository;
import com.codaglobal.repository.ProductRepository;
import com.codaglobal.repository.UserRepository;
import com.codaglobal.util.MessageResponse;
import com.codaglobal.util.OrderStatus;
import com.codaglobal.util.UserDetailsImpl;
import com.codaglobal.util.UserRole;

/**
 * @author Asif Jalaludeen
 *
 */
@Service
public class OrderServiceImpl implements OrderService{
	@Log
	Logger logger;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CartRepository cartRepository;
	
	@Override
	public ResponseEntity<?> getOrderByUser() {
		ResponseEntity<?> response;
		try {
			UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userRepository.findByUsername(userDetails.getUsername()).get();
			logger.info("Gettings orders for user={}", user.getUsername());
			List<Order> orders;
			if(UserRole.SELLER.equals(user.getRole())) {
				orders = orderRepository.findAll();
				orders = orders.stream().filter( order -> {
					String[] productIds = order.getProduct().split(",");
					List<String> products = Arrays.asList(productIds).stream().filter(p -> {
						return productRepository.findById(Long.valueOf(p)).get().getUser().equals(user);
					}).collect(Collectors.toList());
					return products.size() > 0 ? true : false;
				}).collect(Collectors.toList());
			}else {
				orders = orderRepository.findByUser(user);
			}
			response = ResponseEntity.ok(modeltoForm(orders));
		} catch(Exception e) {
			logger.error("Failed to fetch orders");
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseEntity<?> getPendingOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ResponseEntity<?> placeOrder(OrderRequest orderRequest) {
		ResponseEntity<?> response;
		try {
			List<String> products = orderRequest.getCartItems().stream().map(item -> {
				return String.valueOf(item.getProduct().getId());
			}).collect(Collectors.toList());
			orderRequest.getCartItems().stream().forEach(item -> {
				Product product = productRepository.findById(item.getProduct().getId()).get();
				if(product.getStock() > 0) {
					product.setStock(product.getStock() - 1);
					productRepository.save(product);
				}
				cartRepository.deleteById(item.getId());
			});
			UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = userRepository.findByUsername(userDetails.getUsername()).get();
			logger.info("Placing order for user={}", user.getUsername());
			Order order = new Order();
			order.setUser(user);
			Date date = new Date();
			order.setOrderDate(new Timestamp(date.getTime()));
			order.setStatus(OrderStatus.PLACED);
			order.setProduct(String.join(",", products));
			orderRepository.save(order);
			response = ResponseEntity.ok(new MessageResponse("Order placed successfully."));
		}catch(Exception e) {
			logger.error("Failed to place order", e.getMessage());
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseEntity<?> dispatchOrder(Long id) {
		ResponseEntity<?> response;
		try {
			logger.info("Dispatching order with id={}", id);
			Order order = orderRepository.findById(id).get();
			order.setStatus(OrderStatus.DISPATCHED);
			orderRepository.save(order);
			response = ResponseEntity.ok(new MessageResponse("Order dispatched successfully"));
		}catch(Exception e) {
			logger.error("Failed to dispatch order", e.getMessage());
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseEntity<?> receiveOrder(Long id) {
		ResponseEntity<?> response;
		try {
			logger.info("Receiving order with id={}", id);
			Order order = orderRepository.findById(id).get();
			order.setStatus(OrderStatus.DELIVERED);
			orderRepository.save(order);
			response = ResponseEntity.ok(new MessageResponse("Order received successfully"));
		}catch(Exception e) {
			logger.error("Failed to receive order", e.getMessage());
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}

	private List<OrderForm> modeltoForm(List<Order> orders){
		return orders.stream().map(order -> {
			OrderForm orderForm = new OrderForm();
			orderForm.setId(order.getId());
			orderForm.setStatus(order.getStatus().name());
			orderForm.setOrderDate(new Date(order.getOrderDate().getTime()));
			List<String> productNames = Arrays.asList(order.getProduct().split(","))
					.stream().map(e -> {
						return productRepository.findById(Long.parseLong(e)).get().getName();
					}).collect(Collectors.toList());
			orderForm.setProductNames(productNames);
			return orderForm;
		}).collect(Collectors.toList());
	}
}
