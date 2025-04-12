package io.raon.web.example.tyhmleaf;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.raon.web.example.model.User;

@Controller
public class IfElseController {

	@GetMapping("/ifelse/example1")
	public String ifElse(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);
		User userC = new User("userC", 30);

		List<User> userList = List.of(userA, userB, userC);
		model.addAttribute("userList", userList);
		return "ifelse/example1";
	}
}
