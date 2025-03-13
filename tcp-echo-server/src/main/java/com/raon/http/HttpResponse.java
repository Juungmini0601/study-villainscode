package com.raon.http;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 13.
 */
public class HttpResponse {
	private PrintWriter pw;
	private int statusCode = 200;
	private StringBuilder sb = new StringBuilder();
	private String contentType = "text/html; charset=UTF-8";

	public HttpResponse(PrintWriter pw) {
		this.pw = pw;
	}

	public void writeBody(String body) {
		sb.append(body);
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void flush() {
		int contentLength = sb.toString().getBytes(StandardCharsets.UTF_8).length;

		pw.println("HTTP/1.1 " + statusCode + " " + getReasonPhrase(statusCode));
		pw.println("Content-Type: " + contentType);
		pw.println("Content-Length: " + contentLength);
		pw.println();
		pw.println(sb);
		pw.flush();
	}

	private String getReasonPhrase(int statusCode) {
		return switch (statusCode) {
			case 200 -> "OK";
			case 404 -> "Not Found";
			case 500 -> "Internal Server Error";
			case 503 -> "Service Unavailable";
			default -> "Unknown Status Code";
		};

	}
}
