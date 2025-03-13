package com.raon.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 13.
 */
@Slf4j
@RequiredArgsConstructor
public class HttpHandler implements Runnable {

	private final Socket socket;
	private final ServletManager servletManager;

	@Override
	public void run() {
		try (
			socket;
			BufferedReader br = new BufferedReader(
				new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			PrintWriter pw = new PrintWriter(socket.getOutputStream())
		) {
			HttpRequest request = new HttpRequest(br);
			HttpResponse response = new HttpResponse(pw);

			servletManager.execute(request, response);

			response.flush();
		} catch (IOException e) {
			log.error("error on handler: {}", e.getMessage(), e);
		}
	}
}
