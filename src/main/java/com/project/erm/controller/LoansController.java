package com.project.erm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
public class LoansController {

	@GetMapping("/myLoans")
	public String getLoanDetails() {
		return "Welcom to Loan controller";
	}

}
