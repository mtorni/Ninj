package com.test.sockets;
import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {
		try{
		ServerSocket server = new ServerSocket(7777);
		System.out.println("creating a SeverSocket on port 7777");
		Socket client = server.accept();
		System.out.println("client accepted");
		InputStream in = client.getInputStream();
		int i = 0;
		while((i = in.read()) != -1){
			System.out.println((char)i);
			
			
		}
		
		server.close();
		
		
		}catch(Exception e){
			
			System.out.println(e);
		}
       
	}

	
}
