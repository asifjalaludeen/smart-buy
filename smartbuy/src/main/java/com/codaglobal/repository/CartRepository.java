package com.codaglobal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codaglobal.model.Cart;
import com.codaglobal.model.Product;
import com.codaglobal.model.User;

/**
 * @author Asif Jalaludeen
 *
 */
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	List<Cart> findByUser(User user);
	
	//@Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    //public List<Person> find(@Param("lastName") String lastName);
	
	@Query("SELECT c FROM Cart c WHERE c.user = :user AND c.product = :product")
	public List<Cart> findByUserAndProduct(@Param("user") User user, @Param("product") Product product);

}
