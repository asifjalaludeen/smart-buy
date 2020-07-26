package com.codaglobal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codaglobal.model.User;

/**
 * @author Asif Jalaludeen
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Boolean existsByUsername(String username);
	
	Boolean existsByEmailId(String email);
	
	Optional<User> findByUsername(String username);
}
