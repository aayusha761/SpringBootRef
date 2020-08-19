package com.lti.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping(path = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
	public String hello() {
		return "hello Aayush Arora";
	}
}
