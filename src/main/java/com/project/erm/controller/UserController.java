package com.project.erm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.erm.model.Customer;
import com.project.erm.service.CustomerRepository;

@RestController
public class UserController {
	private final PasswordEncoder passwordEncoder;
	private final CustomerRepository customerRepository;

	public UserController(PasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/register")
	public ResponseEntity<Customer> register(Customer customer) {
		return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
	}
}
