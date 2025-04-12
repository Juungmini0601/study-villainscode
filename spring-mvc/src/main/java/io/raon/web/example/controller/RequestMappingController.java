package io.raon.web.example.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/component")
public class RequestMappingController {
	@RequestMapping("/my-controller")
	public String myController() {
		return "controller/component";
	}
}
