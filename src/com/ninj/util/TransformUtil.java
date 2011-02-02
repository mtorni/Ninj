package com.ninj.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public abstract class TransformUtil {

	  public static String transform(String xml, String xsl) throws Exception {


	      StringReader xmlReader = new StringReader(xml);
	      StreamSource xmlSource = new StreamSource(xmlReader);

	      StringReader xslReader = new StringReader(xsl);
	      StreamSource xslSource = new StreamSource(xslReader);


	      TransformerFactory factory = TransformerFactory.newInstance();
	      Transformer transformer = factory.newTransformer(xslSource);


	      StringWriter sw = new StringWriter();

	      StreamResult result = new StreamResult(sw);
	      transformer.transform(xmlSource, result);


	      return sw.toString();

	  }
	
}
