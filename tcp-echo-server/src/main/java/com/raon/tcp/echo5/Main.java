package com.raon.tcp.echo5;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 6.
 */
public class Main {
	public static void main(String[] args) throws Exception {
		Reactor eventLoop = new Reactor(9999);
		Thread eventLoopThread = new Thread(eventLoop);
		eventLoopThread.start();

		System.out.println("server started");
	}
}
