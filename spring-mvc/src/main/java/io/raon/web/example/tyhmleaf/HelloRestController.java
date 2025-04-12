package io.raon.web.example.tyhmleaf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 29.
 */
@RestController
public class HelloRestController {
	@GetMapping("/api/hello")
	public String hello() {
		System.out.println("hi");
		return "Hello World!";
	}
}
