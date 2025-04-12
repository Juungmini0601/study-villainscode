package io.raon.web.example.tyhmleaf;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class BasicObjectController {

	@GetMapping("/basic-object/example1")
	public String basicObject(
		Model model,
		HttpServletRequest request,
		HttpServletResponse response,
		HttpSession session
	) {

		model.addAttribute("request", request);
		model.addAttribute("response", response);
		model.addAttribute("session", session);
		model.addAttribute("servletContext", request.getServletContext());

		return "basic-object/example1";
	}

	@GetMapping("/basic-object/example2")
	public String basicObject2(Model model) {
		model.addAttribute("now", LocalDateTime.now());

		return "basic-object/example2";
	}
}
