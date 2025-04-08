package io.jungmini.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author    : kimjungmin
 * Created on : 2025. 4. 8.
 */
public class EchoHttpHandler implements Runnable {

	private final Socket socket;

	public EchoHttpHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (
			socket;
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
		) {
			// HTTP 파싱하기
			HttpRequest request = new HttpRequest(br);
			System.out.println(request);

			String response = """
					<html>
						<body>
							<h1>Hello World!</h1>
						</body>
					</html
				""";

			bw.write("HTTP/1.1 200 OK\r\n");
			bw.write("Content-Type: text/html; charset=UTF-8\r\n");
			bw.write("Content-Length: " + response.length() + "\r\n");
			bw.write("\r\n");
			bw.write(response);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
