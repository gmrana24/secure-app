package com.project.erm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.erm.model.Customer;

@Service
public class CustomAuthService implements UserDetailsService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomAuthService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
		System.out.println(customer.getEmail());
		List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority(customer.getRole()));

		return new User(customer.getEmail(), customer.getPwd(), roles);
	}
}
