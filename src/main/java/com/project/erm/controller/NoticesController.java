package com.project.erm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
public class NoticesController {

	@GetMapping("/myNotice")
	public String getNoticeDetails() {
		return "Welcom to Notice controller";
	}

}
