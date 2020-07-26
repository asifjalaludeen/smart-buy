package com.codaglobal.service;

import org.springframework.http.ResponseEntity;

import com.codaglobal.payload.LoginRequest;
import com.codaglobal.payload.RegisterRequest;

/**
 * @author Asif Jalaludeen
 *
 */

public interface UserService {
	
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
	
	public ResponseEntity<?> registerUser(RegisterRequest registerRequest);
}
