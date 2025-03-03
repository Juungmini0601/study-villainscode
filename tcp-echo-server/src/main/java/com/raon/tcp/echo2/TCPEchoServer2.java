package com.raon.tcp.echo2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 3.
 */
public class TCPEchoServer2 {
	private static final int PORT = 9999;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("server started on port: " + PORT);
		Socket socket = serverSocket.accept();
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		// accept
		while (true) {
			// message from client
			String received = dis.readUTF();
			if (received.equalsIgnoreCase("exit")) {
				System.out.println("program exit");
				return;
			}

			System.out.println("message from client: " + received);

			// send to client
			String sendToClient = "world";
			dos.writeUTF(sendToClient);
		}

	}
}
