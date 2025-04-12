package io.raon.web.example.tyhmleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TextController {

	@GetMapping("/text/example1")
	public String text(Model model) {
		model.addAttribute("htmlText", "<b>안녕하세요!</b> 이것은 <i>HTML</i> 태그가 포함된 텍스트입니다.");
		model.addAttribute("plainText", "안녕하세요! 일반 텍스트입니다.");

		return "text/example1";
	}
}
