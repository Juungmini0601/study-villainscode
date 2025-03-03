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

		for (int i = 0; i < 10; i++) {
			Socket socket = new Socket("localhost", PORT);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			dos.writeUTF("data" + i);
			String messageFormServer = dis.readUTF();
			System.out.println("messageFormServer = " + messageFormServer);

			dos.writeUTF("exit");
			messageFormServer = dis.readUTF();
			System.out.println("messageFormServer = " + messageFormServer);
		}

	}
}
