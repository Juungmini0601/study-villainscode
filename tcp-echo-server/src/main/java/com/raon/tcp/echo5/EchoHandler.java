package com.raon.tcp.echo5;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 6.
 */
public class EchoHandler implements Handler {
	static final int READING = 0, SENDING = 1;

	private final SocketChannel socketChannel;
	private final SelectionKey selectionKey;
	private final ByteBuffer buffer = ByteBuffer.allocate(256);
	private int state = READING;

	public EchoHandler(Selector selector, SocketChannel socketChannel) throws IOException {
		this.socketChannel = socketChannel;
		this.socketChannel.configureBlocking(false);
		// Selector에 소켓 등록 Read이벤트를 알린다.
		selectionKey = this.socketChannel.register(selector, SelectionKey.OP_READ);
		// Key와 Handler를 연결 Key에 해당하는 이벤트가 발생했을 때 EchoHadnler를 넘겨 받기 위함
		// selectionKey.attachment() -> EchoHandler
		selectionKey.attach(this);
		selector.wakeup();
	}

	@Override
	public void handle() {
		try {
			if (state == READING) {
				read();
			} else if (state == SENDING) {
				send();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	void read() throws IOException {
		int readCount = socketChannel.read(buffer);

		if (readCount == -1) {
			System.out.println("Client disconnected.");
			cleanup();
		} else if (readCount > 0) {
			buffer.flip();
			selectionKey.interestOps(SelectionKey.OP_WRITE);
			state = SENDING;
		}
	}

	void send() throws IOException {
		socketChannel.write(buffer);
		buffer.clear();
		selectionKey.interestOps(SelectionKey.OP_READ);
		state = READING;
	}

	private void cleanup() {
		try {
			selectionKey.cancel();
			socketChannel.close();
		} catch (IOException ex) {
			System.err.println("Error closing connection: " + ex.getMessage());
		}
	}

}
