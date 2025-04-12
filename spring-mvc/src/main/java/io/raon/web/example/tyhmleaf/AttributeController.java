package io.raon.web.example.tyhmleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttributeController {

	@GetMapping("/attribute/example1")
	public String attribute() {
		return "attribute/example1";
	}
}
