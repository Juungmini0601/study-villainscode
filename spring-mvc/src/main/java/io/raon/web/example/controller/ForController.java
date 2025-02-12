package io.raon.web.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.raon.web.example.model.User;

@Controller
public class ForController {

	@GetMapping("/for/example1")
	public String forExample1(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);
		User userC = new User("userC", 30);

		List<User> userList = List.of(userA, userB, userC);
		model.addAttribute("userList", userList);
		return "for/example1";
	}
}
