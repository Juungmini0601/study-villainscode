package com.raon.tcp.echo4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 3.
 */
public class TCPEchoClient4 {
	private static final int PORT = 9999;

	public static void main(String[] args) throws IOException {
		long current = System.currentTimeMillis();

		for (int i = 0; i < 10000; i++) {
			try (Socket socket = new Socket("localhost", PORT);
				 DataInputStream dis = new DataInputStream(socket.getInputStream());
				 DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

				// 메시지 전송
				dos.writeUTF("data" + i);
				dos.flush(); // 버퍼를 비워 명확히 전송

				// 서버 응답 읽기
				String messageFromServer = dis.readUTF();
				System.out.println("messageFromServer = " + messageFromServer);

				// 종료 메시지 전송
				dos.writeUTF("exit");
				dos.flush(); // 버퍼를 비움

				// 종료 응답 읽기
				messageFromServer = dis.readUTF();
				System.out.println("messageFromServer = " + messageFromServer);

				// 소켓 자동 close 처리
			} catch (IOException e) {
				// 문제 발생 시 예외 처리
				e.printStackTrace();
			}
		}

		long endTime = System.currentTimeMillis();
		double seconds = (double)(endTime - current) / 1000;
		// 10000개 0.956초
		System.out.println("수행시간:" + seconds);
	}

}
