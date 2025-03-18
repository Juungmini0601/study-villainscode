package io.raon.web.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import io.raon.web.example.servlet.model.Board;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 18.
 */
// @WebServlet(urlPatterns = "/boards")
public class BoardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Board> boards = Board.makeTestBoards();

		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();

		// 간단한 HTML 출력 생성 Made by ChatGPT
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>Board List</title>");
		writer.println("<style>");
		writer.println("table { width: 100%; border-collapse: collapse; }");
		writer.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
		writer.println("</style>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>Board List</h1>");

		writer.println("<table>");
		writer.println("<thead>");
		writer.println("<tr>");
		writer.println("<th>Author</th>");
		writer.println("<th>Contents</th>");
		writer.println("<th>Comments</th>");
		writer.println("</tr>");
		writer.println("</thead>");
		writer.println("<tbody>");

		// Board 리스트에 따라 각각 HTML 행 생성
		for (Board board : boards) {
			writer.println("<tr>");
			writer.println("<td>" + board.author() + "</td>");
			writer.println("<td>" + board.contents() + "</td>");
			writer.println("<td>" + String.join(", ", board.comments()) + "</td>");
			writer.println("</tr>");
		}

		writer.println("</tbody>");
		writer.println("</table>");

		writer.println("</body>");
		writer.println("</html>");

	}
}
