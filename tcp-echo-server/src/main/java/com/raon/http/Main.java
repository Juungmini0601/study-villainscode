package com.raon.http;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 13.
 */
public class Main {
	public static void main(String[] args) throws Exception {
		HttpServer server = new HttpServer(9999, new ServletManager());
		server.start();
	}
}
