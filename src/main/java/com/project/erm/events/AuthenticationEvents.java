package com.project.erm.events;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

	@EventListener
	public void onSucess(final AuthenticationSuccessEvent event) {
		System.out.println("Login successfull for the user: " + event.getAuthentication().getName());
	}

	@EventListener
	public void onFailure(final AbstractAuthenticationFailureEvent event) {
		System.out.println("Login failed for user: " + event.getAuthentication().getName() + " due to "
				+ event.getException().getMessage());
	}
}
