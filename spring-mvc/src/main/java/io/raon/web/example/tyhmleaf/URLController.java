package io.raon.web.example.tyhmleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class URLController {

	@GetMapping("/url/example1")
	public String url(Model model) {

		model.addAttribute("param1", "value1");
		model.addAttribute("param2", "value2");

		return "url/example1";
	}
}
