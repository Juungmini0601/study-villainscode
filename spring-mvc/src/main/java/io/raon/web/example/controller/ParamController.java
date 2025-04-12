package io.raon.web.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ParamController {
	@GetMapping("/hello-param")
	public String helloParam(@RequestParam String name, @RequestParam(required = false) Long age) {
		log.info("name: {}", name);
		log.info("age: {}", age);
		return "controller/hello-param";
	}

	@GetMapping("/path/{pathId}")
	public String path(@PathVariable Long pathId) {
		log.info("pathId: {}", pathId);
		return "controller/path";
	}
}
