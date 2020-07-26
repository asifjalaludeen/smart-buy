package com.codaglobal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codaglobal.payload.OrderRequest;
import com.codaglobal.service.OrderService;

/**
 * @author Asif Jalaludeen
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping()
	@PreAuthorize("hasAuthority('SELLER') or hasAuthority('BUYER')")
	public ResponseEntity<?> getorders(){
		return orderService.getOrderByUser();
	}
	
	@PostMapping("/place")
	@PreAuthorize("hasAuthority('BUYER')")
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest){
		return orderService.placeOrder(orderRequest);
	}
	
	@PutMapping("/dispatch/{order_id}")
	@PreAuthorize("hasAuthority('SELLER')")
	public ResponseEntity<?> dispatchOrder(@PathVariable("order_id") Long orderId){
		return orderService.dispatchOrder(orderId);
	}
	
	@PutMapping("/receive/{order_id}")
	@PreAuthorize("hasAuthority('BUYER')")
	public ResponseEntity<?> receiveOrder(@PathVariable("order_id") Long orderId){
		return orderService.receiveOrder(orderId);
	}
}
