package com.vebora.oauth2.main.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.vebora.oauth2.cache.Cache;

public class UserIdSupplier {

	public static String getOrCreateUserIdFromHttpRequest(HttpServletRequest req) {
		String userId = getUserIdFromHttpRequest(req);
		if (userId == null) {
			userId = UserIdGenerator.generateString();
		}

		return userId;
	}

	public static String getUserIdFromHttpRequest(HttpServletRequest req) {
		String userId = null;
		if (req.getCookies() != null) {
			for (Cookie cook : req.getCookies()) {
				if (cook.getName().equals(Cache.USER_ID)) {
					userId = cook.getValue();
					break;
				}
			}
		}
		if (userId == null) {
			userId = Cache.getReqMap().get(req);
		}

		return userId;
	}

}
