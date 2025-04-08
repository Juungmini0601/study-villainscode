package io.jungmini.tcp.v3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.slf4j.Slf4j;

/**
 * @author    : kimjungmin
 * Created on : 2025. 4. 7.
 *
 * v3: 여러개의 소켓과 연결되어 데이터를 계속 주고 받는다.
 * TODO Socket 몇개까지 될까 궁금한데?
 */
@Slf4j
public class TCPEchoServerV3 {
	public static final int PORT = 9999;
	public static AtomicLong counter = new AtomicLong(0);

	// nc localhost 9999
	public static void main(String[] args) throws Exception {
		try (
			ExecutorService executorService = Executors.newFixedThreadPool(10);
			ServerSocket serverSocket = new ServerSocket(PORT);
		) {
			log.info("server started on port: {}", PORT);

			while (true) {
				Socket socket = serverSocket.accept();
				executorService.submit(new TCPHandler(socket));
				log.info("client connected: {}", counter.incrementAndGet());
			}
		}
	}

	// TCP Handler
	static class TCPHandler implements Runnable {

		private final Socket socket;

		public TCPHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (
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
			} catch (Exception e) {
				// I/O Excpetion
				log.error(e.getMessage(), e);
			} finally {
				try {
					socket.close();
				} catch (Exception e) {
					// Socket Close Fail
					log.error(e.getMessage(), e);
				}
			}
		}
	}
}
