package com.raon.http;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 13.
 */
public class HomeServlet implements MyHttpServlet {
	@Override
	public void service(HttpRequest request, HttpResponse response) {
		response.writeBody("<h1>Home</h1>");
		response.writeBody("<p>This is Home Page</p>");
		response.writeBody("<a href='/boards?page=1&size=20'>boards</a>");
	}
}
