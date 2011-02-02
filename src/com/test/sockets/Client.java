package com.test.sockets;
import java.net.*;
import java.io.*;

public class Client {

	public static void main(String[] args) {
		try {
			Socket client = new Socket("localhost", 7777);
			System.out.println("Client: " + client);
			OutputStream out = client.getOutputStream();
			out.write("hello".getBytes());
			
		} catch (Exception e) {

			System.out.println(e);
		}
	}
}