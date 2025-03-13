package com.raon.http;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 13.
 */
public class BoardListServlet implements MyHttpServlet {

	@Override
	public void service(HttpRequest request, HttpResponse response) {
		response.writeBody("<h1>BoardList</h1>");
		response.writeBody("<p>BoardList</p>");

		String page = request.getParameter("page");
		String size = request.getParameter("size");

		response.writeBody("<p> page: " + page + "</p>");
		response.writeBody("<p> size: " + size + "</p>");
	}
}
