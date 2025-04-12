package io.raon.web.example.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String showLoginForm(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "error/loginForm";
	}

	/**
	 * @param form (email, password)
	 */
	@PostMapping
	public String login(@Valid @ModelAttribute("loginForm") LoginForm form,
		BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "error/loginForm"; // 유효성 실패 시 다시 폼 보여줌
		}

		boolean loginSuccess = form.getPassword().equals("mypassword");

		if (!loginSuccess) {
			throw new LoginFailedException("이메일 비밀번호가 일치하지 않습니다.");
		}

		return "redirect:/login/success";
	}

	@GetMapping("/success")
	public String loginSuccess() {
		return "error/loginSuccess";
	}
}