package com.ninj.util;

import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public abstract class XMLUtil {

	public static void sendXML(HttpServletResponse response, Document data)
			throws Exception {
		try {
			OutputStream out = response.getOutputStream();

			XMLOutputter op = new XMLOutputter();
			op.output(data, out);
			out.flush();
			out.close();

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
			// throw new DataServiceException(ResponseInfo.UNEXPECTED_ERROR);
		}

	}
	
	
	
	
	public static Document sendXml(String url, Element data) {

		System.out.println("sendDatea called");
		
		Document response = null;
		try {

			// send xml to console
			Document Data_file = new Document(data);
			XMLOutputter xml_out = new XMLOutputter();
			xml_out.output(Data_file, System.out);

			// send xml to url
			String server = url;
			URL u = new URL(server);
			URLConnection uc = u.openConnection();
			HttpURLConnection connection = (HttpURLConnection) uc;
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			OutputStream out = connection.getOutputStream();

			XMLOutputter serializer = new XMLOutputter();
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


}
