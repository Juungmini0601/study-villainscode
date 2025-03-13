package com.raon.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 13.
 */
@Slf4j
public class HttpServer {
	private final ExecutorService es = Executors.newFixedThreadPool(10);
	private final int port;
	private final ServletManager servletManager;

	public HttpServer(int port, ServletManager servletManager) {
		this.port = port;
		this.servletManager = servletManager;
	}

	public void start() throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		log.info("server started on port: {}", port);

		while (true) {
			Socket socket = serverSocket.accept();
			es.submit(new HttpHandler(socket, servletManager));
		}
	}
}
