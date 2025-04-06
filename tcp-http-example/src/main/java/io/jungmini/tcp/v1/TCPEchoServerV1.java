package io.jungmini.tcp.v1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

/**
 * @author    : kimjungmin
 * Created on : 2025. 4. 7.
 */
@Slf4j
public class TCPEchoServerV1 {
	public static final int PORT = 9999;

	// nc localhost 9999
	public static void main(String[] args) throws Exception {
		try (
			ServerSocket serverSocket = new ServerSocket(PORT);

		) {
			log.info("server started on port: {}", PORT);
			try (
				Socket socket = serverSocket.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			) {
				String message = br.readLine();
				System.out.println("message from client: " + message);
				bw.write(message);
				bw.newLine();
				bw.flush();
			}
		}
	}
}
