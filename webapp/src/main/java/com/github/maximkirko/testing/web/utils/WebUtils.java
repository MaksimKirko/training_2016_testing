package com.github.maximkirko.testing.web.utils;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Enumeration;

public class WebUtils {

	public static String[] getCredentials(org.eclipse.jetty.server.Request req) {
		try {
			Enumeration<String> headers = req.getHeaders("Authorization");
			String nextElement = headers.nextElement();
			String base64Credentials = nextElement.substring("Basic".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			return credentials.split(":", 2);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getCurrentUserName(String authHeader) {
		try {
			String base64Credentials = authHeader.substring("Basic".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			return credentials.split(":", 2)[0];
		} catch (Exception e) {
			return null;
		}
	}

}
