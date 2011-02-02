package com.test.url;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class OrderServiceClientCrude {

	public static void main(String args[]) {

		try {
		

			// send xml to console
			
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>sending</root>";
            System.out.print(xml);
	

			// send xml to url
			String server = "http://www.yahoo.com";
			URL u = new URL(server);
			URLConnection uc = u.openConnection();
			HttpURLConnection connection = (HttpURLConnection) uc;
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			OutputStream out = connection.getOutputStream();

			out.write(xml.getBytes());

			// Read the response in xml
			InputStream in = connection.getInputStream();
			int i = 0;
			while((i = in.read()) > -1){
				System.out.print((char)i);
			}
			connection.disconnect();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}