package com.raon.tcp.echo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 3.
 */
public class TCPEchoServer {
	private static final int PORT = 9999;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("server started on port: " + PORT);

		// accept
		Socket socket = serverSocket.accept();
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		// message from client
		String received = dis.readUTF();
		System.out.println("message from client: " + received);

		// send to client
		String sendToClient = "world";
		dos.writeUTF(sendToClient);

	}
}
