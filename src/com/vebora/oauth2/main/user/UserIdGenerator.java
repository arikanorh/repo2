package com.vebora.oauth2.main.user;

import java.util.Random;

public class UserIdGenerator {

	public static String generateString() {
		return String.valueOf(new Random().nextInt());
	}

}
