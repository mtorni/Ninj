package com.test.sockets;

import java.io.*;
import java.net.*;
import java.security.*;

/**
 * Title:        Sample Server
 * Description:  This utility will accept input from a socket, posting back to the socket before closing the link.
 * It is intended as a template for coders to base servers on. Please report bugs to brad at kieser.net
 * Copyright:    Copyright (c) 2002
 * Company:      Kieser.net
 * @author B. Kieser
 * @version 1.0
 */

public class SimpleServer {

  private static int port=4444, maxConnections=0;
  // Listen for incoming connections and handle them
  public static void main(String[] args) {
    int i=0;

    try{
      ServerSocket listener = new ServerSocket(port);
      Socket server;

      while((i++ < maxConnections) || (maxConnections == 0)){
        doComms connection;

        server = listener.accept();
        doComms conn_c= new doComms(server);
        Thread t = new Thread(conn_c);
        t.start();
      }
    } catch (IOException ioe) {
      System.out.println("IOException on socket listen: " + ioe);
      ioe.printStackTrace();
    }
  }

}

class doComms implements Runnable {
    private Socket server;
    private String line,input;

    doComms(Socket server) {
      this.server=server;
    }

    public void run () {

      input="";

      try {
        // Get input from the client
        DataInputStream in = new DataInputStream (server.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));        
        
        PrintStream out = new PrintStream(server.getOutputStream());

        while((line = br.readLine()) != null && !line.equals(".")) {
          input=input + line;
          out.println("I got:" + line);
        }

        // Now write to the client

        System.out.println("Overall message is:" + input);
        out.println("Overall message is:" + input);

        server.close();
      } catch (IOException ioe) {
        System.out.println("IOException on socket listen: " + ioe);
        ioe.printStackTrace();
      }
    }
}