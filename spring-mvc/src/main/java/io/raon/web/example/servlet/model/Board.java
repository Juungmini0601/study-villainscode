package io.raon.web.example.servlet.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 18.
 */
public record Board(
	String author,
	String contents,
	String[] comments
) {

	public static List<Board> makeTestBoards() {
		List<Board> boards = new ArrayList<>();

		for (int i = 1; i <= 20; i++) {
			Board board = new Board(
				"Author " + i,
				"This is the content of board " + i,
				new String[] {"Comment 1", "Comment 2", "Comment 3"}
			);
			boards.add(board);
		}

		return boards;
	}
}
