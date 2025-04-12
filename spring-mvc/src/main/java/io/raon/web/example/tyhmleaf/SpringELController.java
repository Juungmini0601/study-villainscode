package io.raon.web.example.tyhmleaf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.raon.web.example.model.User;

@Controller
public class SpringELController {

	@GetMapping("/springel/example1")
	public String springEL(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);

		List<User> userList = List.of(userA, userB);
		Map<String, User> userMap = Map.of("userA", userA, "userB", userB);

		model.addAttribute("userA", userA);
		model.addAttribute("userList", userList);
		model.addAttribute("userMap", userMap);

		return "springel/example1";
	}
}
