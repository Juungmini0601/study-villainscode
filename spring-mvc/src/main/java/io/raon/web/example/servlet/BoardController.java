package io.raon.web.example.servlet;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.raon.web.example.servlet.model.Board;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 18.
 */
@Controller
public class BoardController {
	@GetMapping("/boards")
	public String getBoards(Model model) {
		List<Board> boards = Board.makeTestBoards(); // 테스트용 Board 리스트 생성
		model.addAttribute("boards", boards); // 모델에 데이터 추가
		return "boards/index.html"; // boards.html 템플릿 파일 반환
	}
}
