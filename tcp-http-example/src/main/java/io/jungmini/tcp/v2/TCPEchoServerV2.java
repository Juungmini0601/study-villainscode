package io.jungmini.tcp.v2;

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
 *
 * v2: 하나의 소켓과 연결되어 데이터를 계속 주고 받는다.
 */
@Slf4j
public class TCPEchoServerV2 {
	public static final int PORT = 9999;

	// nc localhost 9999
	public static void main(String[] args) throws Exception {
		try (
			ServerSocket serverSocket = new ServerSocket(PORT);
		) {
			log.info("server started on port: {}", PORT);
			try (
				// Clinet 연결 대기
				Socket socket = serverSocket.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			) {
				log.info("client connected");
				// 클라이언트 연결 완료 응답을 계속 주고 받음
				while (true) {
					String message = br.readLine();
					System.out.println("message from client: " + message);
					bw.write(message);
					bw.newLine();
					bw.flush();
				}
			}
		}
	}
}
