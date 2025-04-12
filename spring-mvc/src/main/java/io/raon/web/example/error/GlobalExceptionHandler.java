package io.raon.web.example.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LoginFailedException.class)
	public String handleLoginFailed(LoginFailedException ex, Model model) {
		model.addAttribute("errorMessage", ex.getMessage());
		return "error/loginError";
	}

	@ExceptionHandler(Exception.class)
	public String handleGlobalException(Exception ex, Model model) {
		model.addAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
		return "error/commonError";
	}
}