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

public abstract class F1Util {

	public static void setSuccess(HttpServletRequest request) {
		request.setAttribute("f1Submit", "Y");
	}

	public static void setSuccess(HttpServletRequest request, String msg) {
		request.setAttribute("f1Msg", msg);
		request.setAttribute("f1Submit", "Y");
	}

	public static void setSuccess(HttpServletRequest request, String msg,
			String url) {
		request.setAttribute("f1Msg", msg);
		request.setAttribute("f1Url", url);
		request.setAttribute("f1Submit", "Y");
	}

	public static void setError(HttpServletRequest request, String msg,
			String errorFields) {
		request.setAttribute("f1Msg", msg);
		request.setAttribute("f1ErrorFields", errorFields);
		request.setAttribute("f1Submit", "Y");
	}

	public static void setError(HttpServletRequest request, String msg) {
		request.setAttribute("f1Msg", msg);
		request.setAttribute("f1Submit", "Y");
	}
	
	public static void setResult(HttpServletRequest request, String result) {
		request.setAttribute("f1Result", result);
		request.setAttribute("f1Submit", "Y");
	}
	
	public static void setResult(HttpServletRequest request, String result, String url) {
		request.setAttribute("f1Result", result);
		request.setAttribute("f1Url", url);
		request.setAttribute("f1Submit", "Y");
	}

}
