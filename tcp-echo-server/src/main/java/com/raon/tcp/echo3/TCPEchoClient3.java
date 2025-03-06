package com.raon.tcp.echo3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 3.
 */
public class TCPEchoClient3 {
	private static final int PORT = 9999;

	public static void main(String[] args) throws IOException {
		long current = System.currentTimeMillis();

		for (int i = 0; i < 5000; i++) {
			Socket socket = new Socket("localhost", PORT);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			dos.writeUTF("data" + i);
			String messageFormServer = dis.readUTF();
			System.out.println("messageFormServer = " + messageFormServer);

			dos.writeUTF("exit");
			messageFormServer = dis.readUTF();
			System.out.println("messageFormServer = " + messageFormServer);

			dos.close();
			dis.close();
			socket.close();
		}

		long endTime = System.currentTimeMillis();
		double seconds = (double)(endTime - current) / 1000;
		// 5000개 처리하는데 0.7 ~ 1초
		System.out.println("수행시간:" + seconds);
	}
}
