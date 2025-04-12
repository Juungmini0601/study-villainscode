package io.raon.web.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {
	@RequestMapping("/my-controller")
	public String myController() {
		return "controller/my-controller";
	}

	// RequestMethod.POST, PUT ...
	@RequestMapping(value = "/my-controller2", method = RequestMethod.GET)
	public String myController2() {
		return "controller/my-controller2";
	}
}
