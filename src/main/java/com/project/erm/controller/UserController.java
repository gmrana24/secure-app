package com.project.erm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Customer> register(@RequestBody Customer customer) {
		String encodedPassword = passwordEncoder.encode(customer.getPwd());
		customer.setPwd(encodedPassword);
		Customer saved;
		try {
			saved = customerRepository.save(customer);
		} catch (Exception exception) {
			saved = null;
			System.out.println(exception.getMessage());
		}

		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
}
