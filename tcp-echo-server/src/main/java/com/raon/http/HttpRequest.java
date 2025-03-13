package com.raon.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 13.
 */
@Slf4j
@Getter
public class HttpRequest {
	private String method;
	private String path;
	private Header header = new Header();
	private QueryParameters params;

	public HttpRequest(BufferedReader br) {
		parseRequest(br);
		parseHeader(br);
	}

	/**
	 *  HTTP Request Example
	 *  GET /index.html?name=test&age=10 HTTP/1.1
	 *  Host: www.example.com
	 *  User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64)
	 *  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp
	 *  Accept-Language: en-US,en;q=0.5
	 *  Connection: keep-alive
	 */
	private void parseRequest(BufferedReader br) {
		try {
			// GET /index.html?name=test&age=10 HTTP/1.1
			String requestLine = br.readLine();
			if (requestLine == null || requestLine.isEmpty()) {
				throw new IOException("Empty request line");
			}

			String[] parts = requestLine.split(" ");
			if (parts.length != 3) {
				throw new IOException("Invalid request Line: " + requestLine);
			}

			// GET, POST, PUT <- Spec에 따른 검증이 필요하지만.. 생략
			method = parts[0];
			String[] pathParts = parts[1].split("\\?");
			path = pathParts[0];

			if (pathParts.length > 1) {
				params = new QueryParameters(pathParts[1]);
			}
		} catch (IOException e) {
			log.error("error occurred when parsing http request {}", e.getMessage(), e);
		}
	}

	public String getParameter(String key) {
		return this.params.get(key);
	}

	/**
	 *  Host: www.example.com
	 *  User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64)
	 *  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp
	 *  Accept-Language: en-US,en;q=0.5
	 *  Connection: keep-alive
	 */
	private void parseHeader(BufferedReader br) {
		try {
			String line;
			while (!(line = br.readLine()).isEmpty()) {
				String[] headerParts = line.split(":");
				this.header.put(headerParts[0].trim(), headerParts[1].trim());
			}
		} catch (IOException e) {
			log.error("error occurred when parsing http request {}", e.getMessage(), e);
		}
	}

	@Getter
	public static class Header {
		private final Map<String, String> header;

		public Header() {
			this.header = new HashMap<>();
		}

		public void put(String key, String value) {
			this.header.put(key, value);
		}

		public String get(String key) {
			return header.get(key);
		}
	}

	@Getter
	public static class QueryParameters {
		private final Map<String, String> params;

		public QueryParameters() {
			this.params = new HashMap<>();
		}

		/**
		 * @param queryString: name=test&age=10
		 */
		public QueryParameters(String queryString) {
			this();
			System.out.println("queryStringParser called");
			for (String param : queryString.split("&")) {
				String[] keyValue = param.split("=");
				String key = keyValue[0];
				String value = keyValue.length > 1 ? keyValue[1] : ""; // 값이 없으면 빈 문자열 처리
				this.params.put(key, value);
			}
		}

		public String get(String key) {
			return this.params.get(key);
		}
	}
}
