package com.raon.tcp.echo5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 6.
 */
public class Reactor implements Runnable {

	private final Selector selector;
	private final ServerSocketChannel serverSocketChannel;
	private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

	public Reactor(int port) throws IOException {
		selector = Selector.open();

		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		serverSocketChannel.configureBlocking(false);
		SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		selectionKey.attach(new AcceptHandler(selector, serverSocketChannel));
	}

	@Override
	public void run() {
		try {
			while (true) {
				selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				// 이 부분을 좀 별도의 스레드로 빼고 싶었는데 너무 어려워서 포기
				selectedKeys.forEach(key -> dispatch(key));
				selectedKeys.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dispatch(SelectionKey selectionKey) {
		Handler handler = (Handler)selectionKey.attachment();
		handler.handle();
	}
}
