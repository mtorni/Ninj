package com.ninj.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public abstract class UrlUtil {
	
	public static void sendCrude(){
		
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
	
	public static Document sendData(Element Root) {

		System.out.println("sendDatea called");
		
		Document response = null;
		try {

			// send xml to console
			Document Data_file = new Document(Root);
			XMLOutputter xml_out = new XMLOutputter();
			xml_out.output(Data_file, System.out);

			// send xml to url
			String server = "yahoo.com";
			URL u = new URL(server);
			URLConnection uc = u.openConnection();
			HttpURLConnection connection = (HttpURLConnection) uc;
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			OutputStream out = connection.getOutputStream();
			
			System.out.println("CLIENT DATA->");
			XMLOutputter serializer = new XMLOutputter();
			serializer.output(Data_file, System.out);
			serializer.output(Data_file, out);

			
			
			
			out.flush();
			out.close();

			// Read the response in xml
			InputStream in = connection.getInputStream();
			SAXBuilder parser = new SAXBuilder();
			response = parser.build(in);
			
			
			serializer.output(response, System.out);
			in.close();
			
			connection.disconnect();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return response;
	}


	public static String excutePost(String targetURL, String urlParameters) {

		/*
		 * 
		 * The urlParameters is a URL encoded string. String urlParameters =
		 * "fName=" + URLEncoder.encode("???", "UTF-8") + "&lName=" +
		 * URLEncoder.encode("???", "UTF-8")
		 */

		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", ""
					+ Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection
					.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public static void readResponse()throws Exception{

		try {

			// Create a URL for the desired page

			URL url = new URL("http://www.komets.com/press/fw-stats.txt");

			System.out.println(url.getFile());

			System.out.println(url.getUserInfo());

			System.out.println(url.hashCode());

			// Read all the text returned by the server

			BufferedReader in = new BufferedReader(new InputStreamReader(url
					.openStream()));

			String str;

			while ((str = in.readLine()) != null) {

				System.out.println(str);

				// str is one line of text; readLine() strips the newline
				// character(s)

			}

			in.close();

		} catch (Exception e) {

		}

	}

}
