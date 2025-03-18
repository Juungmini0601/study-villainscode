package com.raon.tcp.echo6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author    : kimjungmin
 * Created on : 2025. 3. 15.
 */
public class NettyEchoServer {

	private static final int PORT = 9999;

	public static void main(String[] args) throws Exception {
		// BossGroup은 클라이언트 연결 수락
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(16);

		EchoServerHandler echoServerHandler = new EchoServerHandler();

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline p = ch.pipeline();
						p.addLast(echoServerHandler);
					}
				})
				.option(ChannelOption.SO_BACKLOG, 16384)
				.childOption(ChannelOption.SO_KEEPALIVE, true)
				.childOption(ChannelOption.TCP_NODELAY, true)
				.childOption(ChannelOption.WRITE_BUFFER_WATER_MARK,
					new WriteBufferWaterMark(1024, 2 * 1024)); // 쓰기 버퍼 최적화

			System.out.println("Netty Echo Server started on port: " + PORT);

			ChannelFuture future = bootstrap.bind(PORT).sync();

			future.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
