package com.test.sockets;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

// An example of a very simple socket server.  Start by looking at the
// static main() method at the bottom of this file.

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
public class VerySimpleServer {
	private ServerSocket serverSock = null;

	public VerySimpleServer(int serverPort) {
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
			byte[] buf = new byte[1024];
			int bytes_read = 0;
			try {
				// This call to read() will wait forever, until the
				// program on the other side either sends some data,
				// or closes the socket.
				bytes_read = sockInput.read(buf, 0, buf.length);

				// If the socket is closed, sockInput.read() will return -1.
				if (bytes_read < 0) {
					System.err
							.println("Tried to read from socket, read() returned < 0,  Closing socket.");
					return;
				}
				
				System.err.println("Received " + bytes_read
						+ " bytes, sending them back to client, data="
						+ (new String(buf, 0, bytes_read)));

				StringBuffer response = new StringBuffer();
				response.append("RESPONSE:");
				response.append("HTTP/1.1 101 Web Socket Protocol Handshake");
				response.append("Upgrade: WebSocket");
				response.append("Connection: Upgrade");
				response.append("WebSocket-Origin: ws://localhost:54321");
				response
						.append("WebSocket-Location: ws://localhost:54321");
				response
						.append("WebSocket-Protocol: mySubprotocol.example.org");
				
			
				sockOutput.write(response.toString().getBytes());
				//sockOutput.write(buf, 0, bytes_read);
				// This call to flush() is optional - we're saying go
				// ahead and send the data now instead of buffering
				// it.
				sockOutput.flush();
			} catch (Exception e) {
				System.err
						.println("Exception reading from/writing to socket, e="
								+ e);
				e.printStackTrace(System.err);
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
				// This method call, accept(), blocks and waits
				// (forever if necessary) until some other program
				// opens a socket connection to our server. When some
				// other program opens a connection to our server,
				// accept() creates a new socket to represent that
				// connection and returns.
				sock = serverSock.accept();

				// From this point on, no new socket connections can
				// be made to our server until we call accept() again.

				sockInput = sock.getInputStream();
				sockOutput = sock.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}

			// Do something with the socket - read bytes from the
			// socket and write them back to the socket until the
			// other side closes the connection.
			handleConnection(sockInput, sockOutput);

			// Now we close the socket.
			try {
				System.err.println("Closing socket.");
				sock.close();
			} catch (Exception e) {
				System.err.println("Exception while closing socket.");
				e.printStackTrace(System.err);
			}

			System.err
					.println("Finished with socket, waiting for next connection.");
		}
	}

	public static void main(String argv[]) {
		int port = 54321;
		VerySimpleServer server = new VerySimpleServer(port);
		server.waitForConnections();
	}
}
