package com.sameh.photoapp.api.users.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class TestController {
	@GetMapping
	public String test() {
		return "TEST WORKED";
	}
}
