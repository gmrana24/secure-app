package com.project.erm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CardController {

	@GetMapping("/myCard")
	public String getCardDetails() {
		return "Welcom to Card controller";
	}

}
