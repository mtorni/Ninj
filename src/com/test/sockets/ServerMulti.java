package com.test.sockets;

import java.net.*;
import java.io.*;


/*
 REQUEST:
 GET /Channel HTTP/1.1
 Upgrade: WebSocket
 Connection: Upgrade
 Host: myServer:8876
 Origin: http://myServer:8876
 WebSocket-Protocol: mySubprotocol.example.org


 RESPONSE:
 HTTP/1.1 101 Web Socket Protocol Handshake
 Upgrade: WebSocket
 Connection: Upgrade
 WebSocket-Origin: http://myServer:8876
 WebSocket-Location: ws://myServer:8876/Channel
 WebSocket-Protocol: mySubprotocol.example.org
 */
public class ServerMulti {
	private ServerSocket serverSock = null;

	public ServerMulti(int serverPort) {
		try {
			serverSock = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	// All this method does is wait for some bytes from the
	// connection, read them, then write them back again, until the
	// socket is closed from the other side.
	public void handleConnection(InputStream sockInput, OutputStream sockOutput) {
		while (true) {

			try {

				BufferedReader br = new BufferedReader(new InputStreamReader(sockInput));
				PrintWriter pw = new PrintWriter(sockOutput);
				
				
				
				

			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return;
			}
		}

	}

	public void waitForConnections() {
		Socket sock = null;
		InputStream sockInput = null;
		OutputStream sockOutput = null;
		while (true) {
			try {
				sock = serverSock.accept();
				sockInput = sock.getInputStream();
				sockOutput = sock.getOutputStream();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			handleConnection(sockInput, sockOutput);

			try {
				System.err.println("Closing socket.");
				sock.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			System.err
					.println("Finished with socket, waiting for next connection.");
		}
	}

	public static void main(String argv[]) {
		int port = 7777;
		ServerMulti server = new ServerMulti(port);
		server.waitForConnections();
	}
}
