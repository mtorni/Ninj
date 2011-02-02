package com.test.url;
import java.io.*;
import java.net.*;
public class CrudePost {
	public static void main(String args[]) {
		try {
			// Open a client socket connection
			Socket client = new Socket("63.166.228.125", 80);
           

			System.out.println("Client: " + client);
            getGet(client);
		} catch (UnknownHostException uhe) {
			System.out.println("UnknownHostException: " + uhe);
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe);
		}
	}
	
	
	public static void getGet(Socket clientSocket) {
		try {
			// Acquire the input and output streams
			OutputStream outbound = clientSocket.getOutputStream();
			InputStream inbound = clientSocket.getInputStream();

			// Write the HTTP request to the server

			//outbound.writeBytes("GET / HTTP/1.0\r\n\r\n");
			String s = "GET /datamail/login.jsp HTTP/1.0\r\n\r\n";
			outbound.write(s.getBytes());

			// Read the response
			int i;
			while ((i = inbound.read()) != -1) {
				// Display each line to the console
				System.out.print((char) i);

			}
			outbound.close();
			inbound.close();
			clientSocket.close();
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
	}
	
	public static void getPost(Socket socket) {
		  try {
		        // Construct data
		        String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
		        data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");
		    
		        // Create a socket to the host
		        String hostname = "localhost:8080";
		        int port = 80;
		        InetAddress addr = InetAddress.getByName(hostname);
		     
		    
		        // Send header
		        String path = "/servlet/SomeServlet";
		        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		        wr.write("POST "+path+" HTTP/1.0\r\n");
		        wr.write("Content-Length: "+data.length()+"\r\n");
		        wr.write("Content-Type: application/x-www-form-urlencoded\r\n");
		        wr.write("\r\n");
		    
		        // Send data
		        wr.write(data);
		        wr.flush();
		    
		        // Get response
		        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        String line;
		        while ((line = rd.readLine()) != null) {
		          System.out.println(line);
		        }
		        wr.close();
		        rd.close();
		    } catch (Exception e) {
		    }
	}
}
