package com.ninj.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class WebUtil {

	public static String removeNull(String str) {
		if (str == null) {
			str = "";
		}

		return str;
	}

	public static String removeNull(String str1, String str2) {
		if (str1 == null) {
			str1 = "";
		}
		if ("".equals(str1)) {
			if (str2 != null && !"".equals(str2)) {
				str1 = str2;
			}
		}

		return str1;
	}

	public static String removeNull(String str1, String str2, String str3) {
		if (str1 == null) {
			str1 = "";
		}
		if ("".equals(str1)) {
			if (str2 != null && !"".equals(str2)) {
				str1 = str2;
			}
		}

		if ("".equals(str1)) {
			if (str3 != null && !"".equals(str3)) {
				str1 = str3;
			}
		}

		return str1;
	}

	public static boolean isNullorBlank(String s) {
		boolean flag = false;
		if (s == null || "".equals(s)) {
			flag = true;
		}
		return flag;
	}

	public static String trimString(String str) {
		String ret = "";
		if (str == null) {
			ret = "";
		} else {
			ret = str;
		}
		return ret.trim();
	}

	public static String getSubStringValue(String str, int start, int end) {
		String s = "";
		try {
			s = str.substring(start, end);
		} catch (Exception e) {
			// eat
		}
		return s;
	}

	public static String getSummary(String s, int length) {
		if (s != null && s.length() > length) {
			return s.substring(0, length) + "...";
		} else {
			return s;
		}
	}

	public static String getMessage(HttpServletRequest request) {
		return getMessage(request, "msg");
	}

	public static String getMessage(HttpServletRequest request, String name) {
		String str = null;
		if (request.getAttribute(name) != null) {
			str = (String) request.getAttribute(name);
		} else if (request.getParameter(name) != null) {
			str = (String) request.getParameter(name);
		}
		return str;
	}

	public static String getMessageSafe(HttpServletRequest request) {
		return WebUtil.stripMalicious(getMessage(request));
	}

	public static String getMessageSafe(HttpServletRequest request, String name) {
		return WebUtil.stripMalicious(getMessage(request, name));
	}

	public static void doForward(HttpServletRequest request,
			HttpServletResponse response, String uri) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(uri);
		rd.forward(request, response);
	}

	public static void doRedirect(HttpServletResponse response, String url)
			throws ServletException, IOException {
		response.sendRedirect(response.encodeRedirectURL(url));
	}

	public static String formatCurrency(float obj) {
		String amount = "0.00";

		try {

			NumberFormat numberFormatter = NumberFormat.getNumberInstance();
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			amount = numberFormatter.format(Float.valueOf(String.valueOf(obj)));
		} catch (Exception e) {
			// eat
		}
		return amount;
	}

	public static String formatCurrency(String obj) {
		String amount = "0.00";

		try {

			NumberFormat numberFormatter = NumberFormat.getNumberInstance();
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			amount = numberFormatter.format(Float.valueOf(String.valueOf(obj)));
		} catch (Exception e) {
			// eat
		}
		return amount;
	}

	public static String formatCurrency(double obj) {
		String amount = "0.00";

		try {

			NumberFormat numberFormatter = NumberFormat.getNumberInstance();
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			amount = numberFormatter.format(Float.valueOf(String.valueOf(obj)));
		} catch (Exception e) {
			// eat
		}
		return amount;
	}

	public static String formatCurrencyForOrderWriter(float obj) {
		String amount = "0.00";

		try {

			NumberFormat numberFormatter = NumberFormat.getNumberInstance();
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			amount = numberFormatter.format(Float.valueOf(String.valueOf(obj)));
			amount = amount.replaceAll(",", "");
		} catch (Exception e) {
			// eat
		}
		return amount;
	}

	public static String formatCurrencyForOrderWriter(String obj) {
		String amount = "0.00";

		try {

			NumberFormat numberFormatter = NumberFormat.getNumberInstance();
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			amount = numberFormatter.format(Float.valueOf(String.valueOf(obj)));
			amount = amount.replaceAll(",", "");
		} catch (Exception e) {
			// eat
		}
		return amount;
	}

	public static String formatCurrencyForOrderWriter(double obj) {
		String amount = "0.00";

		try {

			NumberFormat numberFormatter = NumberFormat.getNumberInstance();
			numberFormatter.setMaximumFractionDigits(2);
			numberFormatter.setMinimumFractionDigits(2);
			amount = numberFormatter.format(Float.valueOf(String.valueOf(obj)));
			amount = amount.replaceAll(",", "");
		} catch (Exception e) {
			// eat
		}
		return amount;
	}

	public static String getGUID() throws Exception {
		String guid;
		SecureRandom seeder = new SecureRandom();
		guid = Integer.toHexString(InetAddress.getLocalHost().getHostAddress()
				.hashCode());
		guid += Integer.toHexString((int) System.currentTimeMillis());
		guid += Integer.toHexString(seeder.nextInt());

		return guid;

	}

	public static boolean isValidFormString(String str) {

		Pattern p = Pattern.compile("[A-Za-z0-9.@-_ ]");
		char[] c = str.toCharArray();
		boolean isValid = true;
		for (int x = 0; x < str.length(); x++) {
			char input = c[x];
			Matcher m = p.matcher(String.valueOf(input));
			boolean result = m.find();
			if (!result) {
				isValid = false;
				break;
			}

		}

		return isValid;
	}

	public static String spaceFill(String str, int spots) {
		if (str == null) {
			str = "";
		}
		if (spots < 0) {
			spots = 0;
		}
		if (str.length() > spots) {
			str = str.substring(0, spots);
		}
		int difference = spots - str.length();
		for (int i = 0; difference > i; i++) {
			str = str += " ";
		}
		return str;
	}

	public static String spacePad(String str, int spots) {
		if (str == null) {
			str = "";
		}
		if (spots < 0) {
			spots = 0;
		}
		if (str.length() > spots) {
			str = str.substring(0, spots);
		}
		int difference = spots - str.length();
		for (int i = 0; difference > i; i++) {
			str = " " + str;
		}
		return str;
	}

	public static String zeroFill(String str, int spots) {
		if (str == null) {
			str = "";
		}
		if (spots < 0) {
			spots = 0;
		}
		if (str.length() > spots) {
			str = str.substring(0, spots);
		}
		int difference = spots - str.length();
		for (int i = 0; difference > i; i++) {
			str = str += "0";
		}
		return str;
	}

	public static String zeroPad(String str, int spots) {
		if (str == null) {
			str = "";
		}
		if (spots < 0) {
			spots = 0;
		}
		if (str.length() > spots) {
			str = str.substring(0, spots);
		}
		int difference = spots - str.length();
		for (int i = 0; difference > i; i++) {
			str = "0" + str;
		}
		return str;
	}

	public static boolean isProblemField(List errors, String f) {
		boolean flag = false;
		if (errors != null) {
			for (Iterator it = errors.iterator(); it.hasNext();) {
				String error = (String) it.next();
				if (f.equals(error)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	public static List parseListAttribute(List lt) {
		List new_lt = new ArrayList();
		if (lt != null) {
			new_lt = lt;
		}
		return new_lt;
	}

	public static String parseCreditAbbr(String abbr) {
		String ret = "";

		if ("VI".equals(abbr)) {
			ret = "Visa";
		} else if ("MA".equals(abbr)) {
			ret = "Master Card";
		} else if ("AM".equals(abbr)) {
			ret = "American Express";
		} else if ("DI".equals(abbr)) {
			ret = "Discover";
		}

		return ret;
	}

	public static String parseMonthAbbr(String abbr) {
		String ret = "";

		if ("01".equals(abbr)) {
			ret = "January";
		} else if ("02".equals(abbr)) {
			ret = "February";
		} else if ("03".equals(abbr)) {
			ret = "March";
		} else if ("04".equals(abbr)) {
			ret = "April";
		} else if ("05".equals(abbr)) {
			ret = "May";
		} else if ("06".equals(abbr)) {
			ret = "June";
		} else if ("07".equals(abbr)) {
			ret = "July";
		} else if ("08".equals(abbr)) {
			ret = "August";
		} else if ("09".equals(abbr)) {
			ret = "September";
		} else if ("10".equals(abbr)) {
			ret = "October";
		} else if ("11".equals(abbr)) {
			ret = "November";
		} else if ("12".equals(abbr)) {
			ret = "December";
		}

		return ret;
	}

	public static boolean isAKorHIorPRorVIorGU(String state) {
		boolean flag = false;
		if ("AK".equals(state) || "HI".equals(state) || "PR".equals(state)
				|| "VI".equals(state) || "GU".equals(state)) {
			flag = true;
		}
		return flag;

	}

	public static boolean isContinentalUsAbbr(String abbr) {
		boolean ret = false;
		if ("AL".equals(abbr)) {
			ret = true;
		} else if ("AR".equals(abbr)) {
			ret = true;
		} else if ("AZ".equals(abbr)) {
			ret = true;
		} else if ("CA".equals(abbr)) {
			ret = true;
		} else if ("CO".equals(abbr)) {
			ret = true;
		} else if ("CT".equals(abbr)) {
			ret = true;
		} else if ("DC".equals(abbr)) {
			ret = true;
		} else if ("DE".equals(abbr)) {
			ret = true;
		} else if ("FL".equals(abbr)) {
			ret = true;
		} else if ("GA".equals(abbr)) {
			ret = true;
		} else if ("IA".equals(abbr)) {
			ret = true;
		} else if ("ID".equals(abbr)) {
			ret = true;
		} else if ("IL".equals(abbr)) {
			ret = true;
		} else if ("IN".equals(abbr)) {
			ret = true;
		} else if ("KS".equals(abbr)) {
			ret = true;
		} else if ("KY".equals(abbr)) {
			ret = true;
		} else if ("LA".equals(abbr)) {
			ret = true;
		} else if ("MA".equals(abbr)) {
			ret = true;
		} else if ("MD".equals(abbr)) {
			ret = true;
		} else if ("ME".equals(abbr)) {
			ret = true;
		} else if ("MI".equals(abbr)) {
			ret = true;
		} else if ("MN".equals(abbr)) {
			ret = true;
		} else if ("MO".equals(abbr)) {
			ret = true;
		} else if ("MS".equals(abbr)) {
			ret = true;
		} else if ("MT".equals(abbr)) {
			ret = true;
		} else if ("NC".equals(abbr)) {
			ret = true;
		} else if ("ND".equals(abbr)) {
			ret = true;
		} else if ("NE".equals(abbr)) {
			ret = true;
		} else if ("NH".equals(abbr)) {
			ret = true;
		} else if ("NJ".equals(abbr)) {
			ret = true;
		} else if ("NM".equals(abbr)) {
			ret = true;
		} else if ("NV".equals(abbr)) {
			ret = true;
		} else if ("NY".equals(abbr)) {
			ret = true;
		} else if ("OH".equals(abbr)) {
			ret = true;
		} else if ("OK".equals(abbr)) {
			ret = true;
		} else if ("OR".equals(abbr)) {
			ret = true;
		} else if ("PA".equals(abbr)) {
			ret = true;
		} else if ("RI".equals(abbr)) {
			ret = true;
		} else if ("SC".equals(abbr)) {
			ret = true;
		} else if ("SD".equals(abbr)) {
			ret = true;
		} else if ("TN".equals(abbr)) {
			ret = true;
		} else if ("TX".equals(abbr)) {
			ret = true;
		} else if ("UT".equals(abbr)) {
			ret = true;
		} else if ("VA".equals(abbr)) {
			ret = true;
		} else if ("VT".equals(abbr)) {
			ret = true;
		} else if ("WA".equals(abbr)) {
			ret = true;
		} else if ("WI".equals(abbr)) {
			ret = true;
		} else if ("WV".equals(abbr)) {
			ret = true;
		} else if ("WY".equals(abbr)) {
			ret = true;
		}

		return ret;
	}

	public static boolean isCanadianAbbr(String abbr) {
		boolean ret = false;
		if ("AB".equals(abbr)) {
			ret = true;
		} else if ("BC".equals(abbr)) {
			ret = true;
		} else if ("MB".equals(abbr)) {
			ret = true;
		} else if ("NB".equals(abbr)) {
			ret = true;
		} else if ("NF".equals(abbr)) {
			ret = true;
		} else if ("NW".equals(abbr)) {
			ret = true;
		} else if ("NS".equals(abbr)) {
			ret = true;
		} else if ("ON".equals(abbr)) {
			ret = true;
		} else if ("PI".equals(abbr)) {
			ret = true;
		} else if ("PQ".equals(abbr)) {
			ret = true;
		} else if ("SK".equals(abbr)) {
			ret = true;
		} else if ("YU".equals(abbr)) {
			ret = true;
		} else if ("YT".equals(abbr)) {
			ret = true;
		}
		return ret;
	}

	public static String parseStateAbbr(String abbr) {

		String ret = "";

		if ("AA".equals(abbr)) {
			ret = "Armed Forces - Americas (except Canada)";
		} else if ("AE".equals(abbr)) {
			ret = "Armed Forces - Europe, Canada, Middle East, Africa";
		} else if ("AP".equals(abbr)) {
			ret = "Armed Forces - Pacific";
		} else if ("AL".equals(abbr)) {
			ret = "Alabama";
		} else if ("AK".equals(abbr)) {
			ret = "Alaska";
		} else if ("AB".equals(abbr)) {
			ret = "Alberta";
		} else if ("AS".equals(abbr)) {
			ret = "American Samoa";
		} else if ("AZ".equals(abbr)) {
			ret = "Arizona";
		} else if ("AR".equals(abbr)) {
			ret = "Arkansas";
		} else if ("BC".equals(abbr)) {
			ret = "British Columbia";
		} else if ("CA".equals(abbr)) {
			ret = "California";
		} else if ("CO".equals(abbr)) {
			ret = "Colorado";
		} else if ("CT".equals(abbr)) {
			ret = "Connecticut";
		} else if ("DC".equals(abbr)) {
			ret = "District of Columbia";
		} else if ("DE".equals(abbr)) {
			ret = "Delaware";
		} else if ("FM".equals(abbr)) {
			ret = "Federated States of Micronesia";
		} else if ("FL".equals(abbr)) {
			ret = "Florida";
		} else if ("GA".equals(abbr)) {
			ret = "Georgia";
		} else if ("GU".equals(abbr)) {
			ret = "Guam";
		} else if ("HI".equals(abbr)) {
			ret = "Hawaii";
		} else if ("ID".equals(abbr)) {
			ret = "Idaho";
		} else if ("IL".equals(abbr)) {
			ret = "Illinois";
		} else if ("IN".equals(abbr)) {
			ret = "Indiana";
		} else if ("IA".equals(abbr)) {
			ret = "Iowa";
		} else if ("KS".equals(abbr)) {
			ret = "Kansas";
		} else if ("KY".equals(abbr)) {
			ret = "Kentucky";
		} else if ("LA".equals(abbr)) {
			ret = "Louisiana";
		} else if ("ME".equals(abbr)) {
			ret = "Maine";
		} else if ("MB".equals(abbr)) {
			ret = "Manitoba";
		} else if ("MH".equals(abbr)) {
			ret = "Marshall Islands";
		} else if ("MD".equals(abbr)) {
			ret = "Maryland";
		} else if ("MA".equals(abbr)) {
			ret = "Massachusetts";
		} else if ("MI".equals(abbr)) {
			ret = "Michigan";
		} else if ("MN".equals(abbr)) {
			ret = "Minnesota";
		} else if ("MS".equals(abbr)) {
			ret = "Mississippi";
		} else if ("MO".equals(abbr)) {
			ret = "Missouri";
		} else if ("MT".equals(abbr)) {
			ret = "Montana";
		} else if ("NE".equals(abbr)) {
			ret = "Nebraska";
		} else if ("NV".equals(abbr)) {
			ret = "Nevada";
		} else if ("NB".equals(abbr)) {
			ret = "New Brunswick";
		} else if ("NF".equals(abbr)) {
			ret = "New Foundland";
		} else if ("NH".equals(abbr)) {
			ret = "New Hampshire";
		} else if ("NJ".equals(abbr)) {
			ret = "New Jersey";
		} else if ("NM".equals(abbr)) {
			ret = "New Mexico";
		} else if ("NY".equals(abbr)) {
			ret = "New York";
		} else if ("NC".equals(abbr)) {
			ret = "North Carolina";
		} else if ("ND".equals(abbr)) {
			ret = "North Dakota";
		} else if ("MP".equals(abbr)) {
			ret = "Northern Mariana Islands";
		} else if ("NW".equals(abbr)) {
			ret = "Northwest Territory";
		} else if ("NS".equals(abbr)) {
			ret = "Nova Scotia";
		} else if ("NU".equals(abbr)) {
			ret = "Nunavut";
		} else if ("OH".equals(abbr)) {
			ret = "Ohio";
		} else if ("OK".equals(abbr)) {
			ret = "Oklahoma";
		} else if ("ON".equals(abbr)) {
			ret = "Ontario";
		} else if ("OR".equals(abbr)) {
			ret = "Oregon";
		} else if ("PW".equals(abbr)) {
			ret = "Palau";
		} else if ("PA".equals(abbr)) {
			ret = "Pennsylvania";
		} else if ("PI".equals(abbr)) {
			ret = "Prince Edward Islands";
		} else if ("PR".equals(abbr)) {
			ret = "Puerto Rico";
		} else if ("PQ".equals(abbr)) {
			ret = "Quebec";
		} else if ("RI".equals(abbr)) {
			ret = "Rhode Island";
		} else if ("SK".equals(abbr)) {
			ret = "Saskatchewan";
		} else if ("SC".equals(abbr)) {
			ret = "South Carolina";
		} else if ("SD".equals(abbr)) {
			ret = "South Dakota";
		} else if ("TN".equals(abbr)) {
			ret = "Tennessee";
		} else if ("TX".equals(abbr)) {
			ret = "Texas";
		} else if ("UT".equals(abbr)) {
			ret = "Utah";
		} else if ("VI".equals(abbr)) {
			ret = "Virgin Islands";
		} else if ("VT".equals(abbr)) {
			ret = "Vermont";
		} else if ("VA".equals(abbr)) {
			ret = "Virginia";
		} else if ("WA".equals(abbr)) {
			ret = "Washington";
		} else if ("DC".equals(abbr)) {
			ret = "Washington, DC";
		} else if ("WV".equals(abbr)) {
			ret = "West Virginia";
		} else if ("WI".equals(abbr)) {
			ret = "Wisconsin";
		} else if ("WY".equals(abbr)) {
			ret = "Wyoming";
		} else if ("YU".equals(abbr)) {
			ret = "Yukon";
		} else if ("YT".equals(abbr)) {
			ret = "Yukon Territory";
		}
		return ret;

	}

	public static String xPad(String cnum, int display_length) {
		String ret = "";
		if (cnum != null && cnum.length() > display_length) {
			String s1 = cnum.substring(cnum.length() - display_length);
			StringBuffer sb = new StringBuffer(cnum.length());
			for (int i = 0; i < cnum.length() - display_length; i++) {
				sb.append('X');
			}
			sb.append(s1);
			ret = sb.toString();
		}
		return ret;
	}

	public static String parseDate(Date date, String format) {
		String ret = "";

		SimpleDateFormat f = new SimpleDateFormat("MMMM dd");
		try {
			f = new SimpleDateFormat(format);
		} catch (Exception e) {
			System.out.println("invalid format using default");
			// eat
		}
		ret = f.format(date);
		return ret;
	}

	public static Date parseDate(String date, String format) throws Exception {
		Date d = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			f = new SimpleDateFormat(format);
		} catch (Exception e) {
			System.out.println("invalid format using default");
			// eat
		}
		d = f.parse(date);

		return d;
	}

	public static List paginateList(List lt, int per_page) {

		List page_lt = new ArrayList();
		List all_lt = new ArrayList();
		int count = 0;
		for (Iterator it = lt.iterator(); it.hasNext();) {
			Object o = it.next();
			page_lt.add(o);
			count++;
			if (count % per_page == 0) {
				all_lt.add(page_lt);
				page_lt = new ArrayList();
			}

		}
		if (count % per_page != 0) {
			all_lt.add(page_lt);
		}

		return all_lt;

	}

	public static List getPageList(int number, List all_lt) {

		List page_lt = new ArrayList();

		try {
			page_lt = (List) all_lt.get(number);
		} catch (Exception e) {
		}

		return page_lt;

	}

	public static int getPageNumber(HttpServletRequest request) {
		int number = 0;
		if (request.getParameter("page") != null) {
			try {
				number = Integer.parseInt(request.getParameter("page"));
			} catch (Exception e) {

			}
		}
		return number;
	}

	public static String setGetReferral(HttpServletRequest request, String[] p) {
		boolean flag = true;
		String ref = request.getHeader("Referer");
		if (ref == null) {
			flag = false;
		}

		if (flag) {
			for (int i = 0; i < p.length; i++) {
				if (ref.indexOf(p[i]) > -1) {
					flag = false;
					break;
				}

			}
		}

		if (flag) {
			request.getSession().setAttribute("return_me", ref);
		}

		String s = null;

		if (request.getSession().getAttribute("return_me") != null) {
			s = (String) request.getSession().getAttribute("return_me");
		}

		return s;

	}

	public static String formatForDB(String s) {
		s = s.replaceAll("\r\n", "<br>");
		s = s.replaceAll("\n", "<br>");
		s = s.replaceAll("'", "\\\\'");
		s = s.replaceAll("\"", "&quot;");

		String ss = "";
		char c[] = s.toCharArray();
		for (int x = 0; x < c.length; x++) {
			if ((int) c[x] == 8482) {
				ss += "&trade;";
			} else if ((int) c[x] == 169) {
				ss += "&copy;";
			} else {

				ss += c[x];
			}
		}
		return ss;
	}

	public static String formatFromDB(String s) {
		s = s.replaceAll("\\\\'", "'");
		return s;
	}

	public static ByteArrayInputStream resizeImage(InputStream imageStream,
			int width, int height) throws Exception {
		BufferedImage bi = ImageIO.read(imageStream);

		BufferedImage resizedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Image rescaled = bi.getScaledInstance(width, height,
				Image.SCALE_AREA_AVERAGING);

		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(rescaled, 0, 0, null);
		g.dispose();

		Iterator writers = ImageIO.getImageWritersBySuffix("jpeg");
		ImageWriter writer = (ImageWriter) writers.next();

		ByteArrayOutputStream bos = new ByteArrayOutputStream(32768);

		ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
		writer.setOutput(ios);
		writer.write(null, new IIOImage(resizedImage, null, null), null);
		ios.flush();

		ByteArrayInputStream in = new ByteArrayInputStream(bos.toByteArray());

		return in;

	}

	public static byte[] getBytesFromInputStream(InputStream is)
			throws Exception {

		byte[] bytes = new byte[10485760];

		int offset = 0;
		int numRead = 0;
		while ((numRead = is.read(bytes)) != -1) {
			offset += numRead;
		}

		return bytes;
	}

	public static String convertInputStreamToString(InputStream imageStream)
			throws Exception {
		BufferedReader reader = null;
		String str = "";
		reader = new BufferedReader(new InputStreamReader(imageStream));
		String s = "";
		while ((s = reader.readLine()) != null) {
			str += s;
		}

		return str;
	}

	public static InputStream convertStringToInputStream(String str)
			throws Exception {

		InputStream is = new ByteArrayInputStream(str.getBytes());
		return is;
	}

	public static ByteArrayInputStream getByteArrayInputStream(BufferedImage img)
			throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "jpg", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();

		// convert byte array back to BufferedImage
		ByteArrayInputStream in = new ByteArrayInputStream(imageInByte);
		return in;
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		String ret = null;

		Cookie[] cooks = request.getCookies();
		if (cooks != null) {
			for (int i = 0; i < cooks.length; i++) {
				if (name.equals(cooks[i].getName())) {
					ret = cooks[i].getValue();
					break;// last is first
				}
			}
		}

		return ret;

	}

	public static String millisecondsToString(long t) {
		int milliseconds = (int) (t % 1000);
		int seconds = (int) ((t / 1000) % 60);
		int minutes = (int) ((t / 60000) % 60);
		int hours = (int) ((t / 3600000) % 24);
		String millisecondsStr = (milliseconds < 10 ? "00"
				: (milliseconds < 100 ? "0" : ""))
				+ milliseconds;
		String secondsStr = (seconds < 10 ? "0" : "") + seconds;
		String minutesStr = (minutes < 10 ? "0" : "") + minutes;
		String hoursStr = (hours < 10 ? "0" : "") + hours;
		return new String(hoursStr + ":" + minutesStr + ":" + secondsStr + "."
				+ millisecondsStr);

	}

	public static String stripMalicious(String str) {
		if (str != null) {
			str = str.replaceAll("eval\\((.*)\\)", "");
			str = str.replaceAll(
					"[\\\"\\\'][\\s]*((?i)javascript):(.*)[\\\"\\\']", "\"\"");
			str = str.replaceAll("((?i)script)", "");
		}
		return str;

	}

}
