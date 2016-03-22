package com.vebora.oauth2.cache.services;

import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.vebora.oauth2.main.user.UserCredentialStore;

public class GoogleUserInfoService
{

	public static Userinfoplus getUserInfo(final String userId) throws IOException
	{

		GoogleCredential credential = UserCredentialStore.getUserCredential(userId);

		Oauth2 oauth2 = new Oauth2.Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory(), credential).setApplicationName("myOauthApp").build();

		Userinfoplus userinfo = oauth2.userinfo().get().execute();

		return userinfo;
	}

}
