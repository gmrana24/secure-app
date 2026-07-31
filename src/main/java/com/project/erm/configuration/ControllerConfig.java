package com.project.erm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import com.project.erm.exception.CustomAccessDeniedHandler;
import com.project.erm.exception.CustomAuthenticationEntryPoint;

@Configuration
public class ControllerConfig {

	@Bean
	@Profile("prod")
	public SecurityFilterChain filterChainProd(HttpSecurity http) throws Exception {
		http
				.redirectToHttps(x -> x.requestMatchers(AnyRequestMatcher.INSTANCE))
				.sessionManagement(session -> session.maximumSessions(1).maxSessionsPreventsLogin(true))
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(req -> req
						.requestMatchers("/register", "/error").permitAll()
						.requestMatchers("/api/**").authenticated());
		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		// http.exceptionHandling(x -> x.accessDeniedHandler(new
		// CustomAccessDeniedHandler()));
		// http.exceptionHandling(x -> x.authenticationEntryPoint(new
		// CustomAuthenticationEntryPoint()));
		return http.build();
	}

	@Bean
	@Profile("dev")
	public SecurityFilterChain filterChainDev(HttpSecurity http) throws Exception {
		http
				.sessionManagement(session -> session.sessionFixation(fixation -> fixation.newSession()).maximumSessions(1)
						.maxSessionsPreventsLogin(true))
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(req -> req
						.requestMatchers("/register", "/login", "/error").permitAll()
						.requestMatchers("/api/**").authenticated());
		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		// http.exceptionHandling(exc -> exc.accessDeniedHandler(new
		// CustomAccessDeniedHandler()));
		// http.exceptionHandling(exc -> exc.authenticationEntryPoint(new
		// CustomAuthenticationEntryPoint()));
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public CompromisedPasswordChecker compromisedPasswordChecker() {
		return new HaveIBeenPwnedRestApiPasswordChecker();
	}
}
