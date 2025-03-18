package com.raon.tcp.echo3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 3.
 */
public class TCPEchoServer3 {
	private static final int PORT = 9999;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("server started on port: " + PORT);

		// VirtualThreadPerTaskExecutor 사용
		var executorService = Executors.newVirtualThreadPerTaskExecutor();

		// accept
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("socket connected: " + socket);

			executorService.execute(() -> handleSocket(socket));
		}
		// C10K 성공 0.8초?!
	}

	private static void handleSocket(Socket socket) {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			while (true) {
				String messageFromClient = dis.readUTF();
				System.out.println("message from client: " + messageFromClient);

				if (messageFromClient.equalsIgnoreCase("exit")) {
					// 실제로는 소켓 닫아줘야 하는데 예제라서 간단하게 작성
					dos.writeUTF("goodBye");
					System.out.println("socket release");
					dis.close();
					dos.close();
					socket.close();
					return;
				}

				String response = "responseFromServer";
				dos.writeUTF(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
