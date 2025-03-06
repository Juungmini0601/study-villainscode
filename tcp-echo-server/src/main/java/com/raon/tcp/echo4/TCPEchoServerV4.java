package com.raon.tcp.echo4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 3.
 *
 * Java NIO와 I/O 멀티플렉싱 기반의 다중 접속 서버
 * 코드출처: https://engineering.linecorp.com/ko/blog/do-not-block-the-event-loop-part2
 *
 * 채널과 버퍼 기반 I/O API
 * Non Blocking I/O 지원
 * Selector를 이용한 I/O 멀티플렉싱 지원
 */
// public class TCPEchoServerV4 {
// 	private static final int PORT = 9999;
//
// 	public static void main(String[] args) throws Exception {
// 		// 소켓을 모니터링 하기 위한 셀렉터 생성
// 		Selector selector = Selector.open();
// 		// 소켓 Non Blocking Mode 설정
// 		ServerSocketChannel serverSocket = ServerSocketChannel.open();
// 		serverSocket.bind(new InetSocketAddress("localhost", PORT));
// 		serverSocket.configureBlocking(false);
// 		// 소켓을 셀렉터에 등록
// 		serverSocket.register(selector, SelectionKey.OP_ACCEPT);
// 		ByteBuffer buffer = ByteBuffer.allocate(256);
//
// 		System.out.println("server started on port: " + PORT);
//
// 		while (true) {
// 			// 모니터중인 소켓에서 이벤트 확인
// 			selector.select();
// 			Set<SelectionKey> selectionKeys = selector.selectedKeys();
// 			Iterator<SelectionKey> iterator = selectionKeys.iterator();
// 			// 이벤트 타입에 따른 처리
// 			while (iterator.hasNext()) {
// 				SelectionKey key = iterator.next();
//
// 				// Case Server Socket Channel
// 				if (key.isAcceptable()) {
// 					register(selector, serverSocket);
// 				}
// 				// Case Client Socket Read Data
// 				if (key.isReadable()) {
// 					answerWithEcho(buffer, key);
// 				}
//
// 				iterator.remove();
// 			}
// 		}
// 	}
//
// 	private static void answerWithEcho(ByteBuffer buffer, SelectionKey key) throws Exception {
// 		SocketChannel clientSocket = (SocketChannel)key.channel();
// 		clientSocket.read(buffer);
//
// 		if (new String(buffer.array()).trim().equalsIgnoreCase("exit")) {
// 			clientSocket.close();
// 			System.out.println("close socket");
// 		}
// 		// 버퍼 모드 전환
// 		buffer.flip();
// 		clientSocket.write(buffer);
// 		buffer.clear();
// 	}
//
// 	private static void register(Selector selector, ServerSocketChannel serverSocket) throws Exception {
// 		SocketChannel clientSocket = serverSocket.accept();
// 		clientSocket.configureBlocking(false);
// 		clientSocket.register(selector, SelectionKey.OP_READ);
// 		System.out.println("connected new client");
// 	}
// }
public class TCPEchoServerV4 {
	private static final int PORT = 9999;

	public static void main(String[] args) {
		Selector selector = null;
		ServerSocketChannel serverSocket = null;

		try {
			// 셀렉터와 서버 소켓 초기화
			selector = Selector.open();
			serverSocket = ServerSocketChannel.open();
			serverSocket.bind(new InetSocketAddress("localhost", PORT));
			serverSocket.configureBlocking(false);
			serverSocket.register(selector, SelectionKey.OP_ACCEPT);

			ByteBuffer buffer = ByteBuffer.allocate(256);
			System.out.println("Server started on port: " + PORT);

			boolean running = true;
			while (running) {
				// 소켓 이벤트 기다림
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();

				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();

					// 연결 이벤트
					if (key.isAcceptable()) {
						register(selector, serverSocket);
					}

					// 읽기 이벤트
					if (key.isReadable()) {
						try {
							answerWithEcho(buffer, key);
						} catch (IOException e) {
							closeKeyAndChannel(key); // 오류 발생 시 소켓 정리
						}
					}

					// 처리한 키는 반드시 제거해야 함
					iterator.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 종료 시 자원 해제
			try {
				if (selector != null)
					selector.close();
				if (serverSocket != null)
					serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Server shut down.");
		}
	}

	private static void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
		SocketChannel clientSocket = serverSocket.accept();
		clientSocket.configureBlocking(false);
		clientSocket.register(selector, SelectionKey.OP_READ);
		System.out.println("Connected new client");
	}

	private static void answerWithEcho(ByteBuffer buffer, SelectionKey key) throws IOException {
		SocketChannel clientSocket = (SocketChannel)key.channel();
		int bytesRead = clientSocket.read(buffer);

		// 클라이언트가 연결을 끊었는지 확인
		if (bytesRead == -1) {
			closeKeyAndChannel(key);
			System.out.println("Client disconnected.");
			return;
		}

		String message = new String(buffer.array(), 0, bytesRead).trim();
		if (message.equalsIgnoreCase("exit")) {
			closeKeyAndChannel(key);
			System.out.println("Client requested to close connection.");
			return;
		}

		// Echo 메시지 전송
		buffer.flip();
		clientSocket.write(buffer);
		buffer.clear();
	}

	private static void closeKeyAndChannel(SelectionKey key) throws IOException {
		if (key != null) {
			key.cancel();
			if (key.channel() != null) {
				key.channel().close();
			}
		}
	}
}

