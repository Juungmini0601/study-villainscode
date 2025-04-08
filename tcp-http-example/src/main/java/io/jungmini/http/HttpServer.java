package io.jungmini.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * @author    : kimjungmin
 * Created on : 2025. 4. 8.
 */
@Slf4j
public class HttpServer {
	private final ExecutorService es = Executors.newFixedThreadPool(10);
	private final int port;

	public HttpServer(int port) {
		this.port = port;
	}

	public void start() throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			log.info("Server started on port {}", port);
			while (true) {
				es.submit(new EchoHttpHandler(serverSocket.accept()));
			}
		}
	}
}
