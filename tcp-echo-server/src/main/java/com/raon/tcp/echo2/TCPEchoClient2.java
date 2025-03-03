package com.raon.tcp.echo2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 3.
 */
public class TCPEchoClient2 {
	private static final int PORT = 9999;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", PORT);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		System.out.println("socket: " + socket);

		while (true) {
			// send String to Server
			String toSendString = sc.nextLine();
			if (toSendString.equalsIgnoreCase("exit")) {
				System.out.println("program exit");
				dos.writeUTF("exit");
				return;
			}
			dos.writeUTF(toSendString);

			// String from Server
			String received = dis.readUTF();
			System.out.println("message Form Server: " + received);
		}

	}
}
