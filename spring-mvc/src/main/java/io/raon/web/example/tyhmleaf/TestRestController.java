package io.raon.web.example.tyhmleaf;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestRestController {

	@PostMapping("/api/v1/test/{id}")
	public String test(
		@PathVariable("id") Integer id,
		@RequestParam(value = "param1") String param1,
		@RequestBody String message) {
		log.info("id: {}, name: {}, message: {}", id, param1, message);

		return "ok";
	}
}
