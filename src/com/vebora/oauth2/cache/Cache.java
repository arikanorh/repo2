package com.vebora.oauth2.cache;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Cache {

	public static String USER_ID = "oauid";

	private static Map<String, String> userMap = new HashMap<>();

	private static Map<HttpServletRequest, String> reqMap = new HashMap<>();

	public static Map<String, String> getUserMap() {
		return userMap;
	}

	public static Map<HttpServletRequest, String> getReqMap() {
		return reqMap;
	}

}
