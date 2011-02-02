package com.test.url;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class OrderServiceClient {

	static final String url = "https://webservices.datapakservices.com/OrderServiceTest";//"http://localhost:8080/XmlOrderService";
	
	
	public static void main(String args[]) {

		try {

			// create xml


			// datapak order file
			Element DatapakServices = new Element("DatapakServices");
			DatapakServices.setAttribute("method","submit_order");

			// source
			Element Source = new Element("Source");

			Element ID = new Element("ID");
			ID.setText("IMP");
			Source.addContent(ID);

			Element Username = new Element("Username");
			Username.setText("mtorni");
			Source.addContent(Username);

			Element Password = new Element("Password");
			Password.setText("mtorni123");
			Source.addContent(Password);

			DatapakServices.addContent(Source);
			// end source

			// order

			Element Order = new Element("Order");
			Order.setAttribute("method", "order");

			Element CompanyNumber = new Element("CompanyNumber");
			CompanyNumber.setText("122");
			Order.addContent(CompanyNumber);

			Element ProjectNumber = new Element("ProjectNumber");
			ProjectNumber.setText("001");
			Order.addContent(ProjectNumber);

			Element SourceCode = new Element("SourceCode");
			SourceCode.setText("33");
			Order.addContent(SourceCode);
			
			Element TrackingCode = new Element("TrackingCode");
			TrackingCode.setText("100");
			Order.addContent(TrackingCode);

			Element MediaCode = new Element("MediaCode");
			MediaCode.setText("033");
			Order.addContent(MediaCode);

			Element OrderDate = new Element("OrderDate");
			OrderDate.setText("12/23/2006");
			Order.addContent(OrderDate);
			
			Element OrderTime = new Element("OrderTime");
			OrderTime.setText("13:54");
			Order.addContent(OrderTime);

			
			// shipping method
			Element ShippingMethod = new Element("ShippingMethod");
			ShippingMethod.setText("01");
			Order.addContent(ShippingMethod);

			// end shipping method

			// billing info

			Element BillingInfo = new Element("BillingInfo");
			
			Element BillingFirstName = new Element("FirstName");
			BillingFirstName.setText("John");
			BillingInfo.addContent(BillingFirstName);

			Element BillingLastName = new Element("LastName");
			BillingLastName.setText("Doe");
			BillingInfo.addContent(BillingLastName);
			
			Element BillingAddress1 = new Element("Address1");
			BillingAddress1.setText("123 Anywhere Lane");
			BillingInfo.addContent(BillingAddress1);

			Element BillingAddress2 = new Element("Address2");
			BillingAddress2.setText("Suite 321");
			BillingInfo.addContent(BillingAddress2);

			Element BillingCity = new Element("City");
			BillingCity.setText("New Hudson");
			BillingInfo.addContent(BillingCity);

			Element BillingState = new Element("State");
			BillingState.setText("MI");
			BillingInfo.addContent(BillingState);

			Element BillingZip = new Element("BillingZip");
			BillingZip.setText("48465");
			BillingInfo.addContent(BillingZip);

			Element BillingCountry = new Element("Country");
			BillingCountry.setText("US");
			BillingInfo.addContent(BillingCountry);

			Element BillingPhone = new Element("BillingPhone");
			BillingPhone.setText("2485551234");
			BillingInfo.addContent(BillingPhone);

			Element BillingEmail = new Element("Email");
			BillingEmail.setText("john@doe.com");
			BillingInfo.addContent(BillingEmail);

			Order.addContent(BillingInfo);
			// end billing info

			// shipping info

			Element ShippingInfo = new Element("ShippingInfo");
			
			Element ShippingFirstName = new Element("FirstName");
			ShippingFirstName.setText("John");
			ShippingInfo.addContent(ShippingFirstName);

			Element ShippingLastName = new Element("LastName");
			ShippingLastName.setText("Doe");
			ShippingInfo.addContent(ShippingLastName);
			
			Element ShippingAddress1 = new Element("Address1");
			ShippingAddress1.setText("123 Anywhere Lane");
			ShippingInfo.addContent(ShippingAddress1);

			Element ShippingAddress2 = new Element("Address2");
			ShippingAddress2.setText("Suite 321");
			ShippingInfo.addContent(ShippingAddress2);

			Element ShippingCity = new Element("City");
			ShippingCity.setText("New Hudson");
			ShippingInfo.addContent(ShippingCity);

			Element ShippingState = new Element("State");
			ShippingState.setText("MI");
			ShippingInfo.addContent(ShippingState);

			Element ShippingZip = new Element("ShippingZip");
			ShippingZip.setText("48465");
			ShippingInfo.addContent(ShippingZip);

			Element ShippingCountry = new Element("Country");
			ShippingCountry.setText("US");
			ShippingInfo.addContent(ShippingCountry);

			Element ShippingPhone = new Element("Phone");
			ShippingPhone.setText("2485551234");
			ShippingInfo.addContent(ShippingPhone);

			Element ShippingEmail = new Element("Email");
			ShippingEmail.setText("john@doe.com");
			ShippingInfo.addContent(ShippingEmail);

            Order.addContent(ShippingInfo);
            
			// end shipping info

			// payment info
            
			Element PaymentInfo = new Element("PaymentInfo");
			
			Element Type = new Element("Type");
			Type.setText("VI");
			PaymentInfo.addContent(Type);

			// cc payment info

			Element CardNumber = new Element("CardNumber");
			CardNumber.setText("4123456789128");
			PaymentInfo.addContent(CardNumber);

			Element ExpirationMonth = new Element("ExpirationMonth");
			ExpirationMonth.setText("03");
			PaymentInfo.addContent(ExpirationMonth);

			Element ExpirationYear = new Element("ExpirationYear");
			ExpirationYear.setText("2006");
			PaymentInfo.addContent(ExpirationYear);

			// end cc payment info

			// check payment info
			
			Element RoutingNumber = new Element("RoutingNumber");
			RoutingNumber.setText("2342342342");
			PaymentInfo.addContent(RoutingNumber);

 
			Element AccountNumber = new Element("AccountNumber");
			AccountNumber.setText("12342342");
			PaymentInfo.addContent(AccountNumber);
			
			Element CheckNumber = new Element("CheckNumber");
			CheckNumber.setText("1234");
            PaymentInfo.addContent(CheckNumber);
			
			// end check payment info
       
		
			Element NumberOfPayments = new Element("NumberOfPayments");
			NumberOfPayments.setText("3");
			PaymentInfo.addContent(NumberOfPayments);

			Element Payment1 = new Element("Payment");
			Payment1.setAttribute("number", "1");
			Payment1.setText("14.99");
			PaymentInfo.addContent(Payment1);
			
			Element Payment2 = new Element("Payment");
			Payment2.setAttribute("number", "2");
			Payment2.setText("89.99");
			PaymentInfo.addContent(Payment2);
			
			Element Payment3 = new Element("Payment");
			Payment3.setAttribute("number", "3");
			Payment3.setText("89.00");
			PaymentInfo.addContent(Payment3);
			
			

		
			
			
			// merchandise total

			Element MerchandiseTotal = new Element("MerchandiseTotal");
			MerchandiseTotal.setText("123.00");
			PaymentInfo.addContent(MerchandiseTotal);

			// end merchandise total

			// shipping and handling

			Element ShippingCost = new Element("ShippmentCost");
			ShippingCost.setText("3.00");
			PaymentInfo.addContent(ShippingCost);
			
			Element PriorityHandling = new Element("PriorityHandling");
			PriorityHandling.setText("1.00");
			PaymentInfo.addContent(PriorityHandling);

			// end shipping and handling

			// sales tax

			Element SalesTax = new Element("SalesTax");
            SalesTax.setText("2.00");
            PaymentInfo.addContent(SalesTax);
            
			// end sales tax

			// order total

			Element OrderTotal = new Element("OrderTotal");
			OrderTotal.setText("194.97");
			PaymentInfo.addContent(OrderTotal);

			// end order total
 
			Order.addContent(PaymentInfo);
			
            // end payment info
			
			// item 1

			Element Item1 = new Element("Item");

			Element Item1Code = new Element("ItemCode");
			Item1Code.setText("1234");
			Item1.addContent(Item1Code);

			Element Item1Sequence = new Element("ItemSequence");
			Item1Sequence.setText("01");
			Item1.addContent(Item1Sequence);

			Element Item1Quantity = new Element("Quantity");
			Item1Quantity.setText("4");
			Item1.addContent(Item1Quantity);

			Element Item1Price = new Element("Price");
			Item1Price.setText("12.00");
			Item1.addContent(Item1Price);

			Element Item1Upsell = new Element("Upsell");
			Item1Upsell.setText("Y");
			Item1.addContent(Item1Upsell);

			// continuity
			Element Item1Continuity1 = new Element("Continuity");

			Element Item1Continuity1ItemCode = new Element("ItemCode");
			Item1Continuity1ItemCode.setText("4321");
			Item1Continuity1.addContent(Item1Continuity1ItemCode);

			Element Item1Continuity1ItemSequence = new Element("ItemSequence");
			Item1Continuity1ItemSequence.setText("01");
			Item1Continuity1.addContent(Item1Continuity1ItemSequence);

			Element Item1Continuity1Frequency = new Element("Frequency");
			Item1Continuity1Frequency.setText("090");
			Item1Continuity1.addContent(Item1Continuity1Frequency);

			Element Item1Continuity1Quantity = new Element("Quantity");
			Item1Continuity1Quantity.setText("1");
			Item1Continuity1.addContent(Item1Continuity1Quantity);

			Element Item1Continuity1Price = new Element("Price");
			Item1Continuity1Price.setText("12.00");
			Item1Continuity1.addContent(Item1Continuity1Price);

			Element Item1Continuity1ShippingCost = new Element("ShippingCost");
			Item1Continuity1ShippingCost.setText("13.00");
			Item1Continuity1.addContent(Item1Continuity1ShippingCost);

			Item1.addContent(Item1Continuity1);
			// end item 1
			
			Order.addContent(Item1);
			// end order

			DatapakServices.addContent(Order);
			// end datapak order
			


			// send xml to console
			Document order_file = new Document(DatapakServices);
			XMLOutputter xml_out = new XMLOutputter();
			xml_out.output(order_file, System.out);

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
			serializer.output(order_file, out);

			out.flush();
			out.close();

			// Read the response in xml
			InputStream in = connection.getInputStream();
			SAXBuilder parser = new SAXBuilder();
			Document response = parser.build(in);
			serializer.output(response, System.out);
			in.close();
			connection.disconnect();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
