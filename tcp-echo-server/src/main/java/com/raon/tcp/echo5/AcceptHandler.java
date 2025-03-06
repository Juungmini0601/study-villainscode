package com.raon.tcp.echo5;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import lombok.RequiredArgsConstructor;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 6.
 */
@RequiredArgsConstructor
public class AcceptHandler implements Handler {

	private final Selector selector;
	private final ServerSocketChannel serverSocketChannel;

	@Override
	public void handle() {
		try {
			SocketChannel socketChannel = serverSocketChannel.accept();

			if (socketChannel != null) {
				// Client와 연결된 Handler를 만든다.
				// 만들면서 Selector에 소켓 등록 함.
				new EchoHandler(selector, socketChannel);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
