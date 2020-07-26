package com.codaglobal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codaglobal.model.Product;
import com.codaglobal.model.User;
import com.codaglobal.util.ProductCategory;

/**
 * @author Asif Jalaludeen
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByCategory(ProductCategory category);
	List<Product> findByUser(User user);
}
