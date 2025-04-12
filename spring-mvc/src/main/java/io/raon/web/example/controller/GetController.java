package io.raon.web.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GetController {
	@GetMapping("/get")
	public String getMapping() {
		return "controller/get";
	}

	@PostMapping("/post")
	public String postMapping() {
		return "controller/post";
	}
}
