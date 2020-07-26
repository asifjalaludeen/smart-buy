package com.codaglobal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codaglobal.infrastructure.Log;
import com.codaglobal.model.User;
import com.codaglobal.payload.LoginRequest;
import com.codaglobal.payload.RegisterRequest;
import com.codaglobal.repository.UserRepository;
import com.codaglobal.util.JwtResponse;
import com.codaglobal.util.JwtUtil;
import com.codaglobal.util.MessageResponse;
import com.codaglobal.util.UserDetailsImpl;
import com.codaglobal.util.UserRole;

/**
 * @author Asif Jalaludeen
 *
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtil jwtUtils;
	@Log
	Logger logger;
	
	@Override
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		ResponseEntity<?> response;
		try {
			logger.info("Authenticating user with username={}", loginRequest.getUsername());
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
			logger.info("User authenticated with username={}", loginRequest.getUsername());
			response = ResponseEntity.ok(new JwtResponse(jwt, 
					 userDetails.getId(), 
					 userDetails.getUsername(), 
					 userDetails.getEmail(), 
					 roles));
		}catch(Exception e) {
			logger.error("Authentication failed with error={}", e.getMessage());
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}

	@Override
	@Transactional
	public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
		ResponseEntity<?> response;
		try {
			logger.info("Registering user with username={}", registerRequest.getUsername());
			if(userRepository.existsByUsername(registerRequest.getUsername())){
				logger.error("Username is already taken username={}", registerRequest.getUsername());
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Username is already taken!"));
			}
			if(userRepository.existsByEmailId(registerRequest.getEmailId())) {
				logger.error("Email is already in use username={}", registerRequest.getUsername());
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Email is already in use!"));
			}
			
			User user = new User(registerRequest.getName(), registerRequest.getEmailId(), registerRequest.getPhoneNumber(),
					registerRequest.getUsername(), encoder.encode(registerRequest.getPassword()), UserRole.valueOf(registerRequest.getRole()));
			userRepository.save(user);
			response = ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}catch(Exception e) {
			logger.error("user registration failed with error={}", e.getMessage());
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return response;
	}

}
