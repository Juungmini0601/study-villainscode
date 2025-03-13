package com.raon.http;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 13.
 */
@Slf4j
public class ServletManager {
	private final Map<String, MyHttpServlet> map = new HashMap<>();

	public ServletManager() {
		map.put("/", new HomeServlet());
		map.put("/boards", new BoardListServlet());
	}

	public void execute(HttpRequest request, HttpResponse response) {
		MyHttpServlet servlet = map.get(request.getPath());

		if (servlet == null) {
			log.error("not found servlet");
			return;
		}

		servlet.service(request, response);
	}
}
