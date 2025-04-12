package io.raon.web.example.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author    : kimjungmin
 * Created on : 2025. 4. 13.
 */
@Controller
public class TestController {

	@GetMapping("/interceptor-test")
	public String interceptorTest() {
		return "interceptor/test";
	}
}
