package com.codaglobal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codaglobal.model.Order;
import com.codaglobal.model.User;

/**
 * @author Asif Jalaludeen
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
	public List<Order> findByUser(User user);
}
