package com.raon.tcp.echo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 3.
 */
public class TCPEchoClient {
	private static final int PORT = 9999;

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", PORT);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		System.out.println("socket: " + socket);

		// send String to Server
		String toSendString = "Hello";
		dos.writeUTF(toSendString);

		// String from Server
		String received = dis.readUTF();
		System.out.println("message Form Server: " + received);
	}
}
