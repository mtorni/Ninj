package com.test.sockets;

import java.net.*;
import java.io.*;

public class EchoServer {
	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(4444);
			System.out.println("Server: " + serverSocket);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}

		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
			System.out.println("Client Accepted: " + clientSocket);
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		// PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
		// true);
		OutputStream out = clientSocket.getOutputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

		// out.println("Welcome new client.");
		out.write("Welcome new client\n".getBytes());
		out.flush();

		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.err.println(inputLine);
			// out.println("I heard: " + inputLine);
			inputLine = "I heard: " + inputLine + "\n";
			out.write(inputLine.getBytes());
			out.flush();
		}
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
}
