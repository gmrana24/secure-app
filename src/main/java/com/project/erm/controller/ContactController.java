package com.project.erm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContactController {

	@GetMapping("/myContact")
	public String getContactDetails() {
		return "Welcom to Contact controller";
	}

}
