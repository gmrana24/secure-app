package com.project.erm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomAuthenticationProvider(final UserDetailsService userDetailsService,
			final PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	public Authentication authenticate(final Authentication authentication) {
		final String username = authentication.getName(), pwd = authentication.getCredentials().toString();
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if (passwordEncoder.matches(pwd, userDetails.getPassword()))
			return new UsernamePasswordAuthenticationToken(username, pwd, userDetails.getAuthorities());
		else
			throw new BadCredentialsException("Invalid Password");

	}

	public boolean supports(final Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
