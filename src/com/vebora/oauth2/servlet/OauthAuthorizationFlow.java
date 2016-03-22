package com.vebora.oauth2.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.oauth2.Oauth2Scopes;
import com.vebora.oauth2.cache.Constants;
import com.vebora.oauth2.cache.OauthDataStore;

public class OauthAuthorizationFlow
{

	public static AuthorizationCodeFlow newFlow() throws IOException
	{
		List<String> SCOPES = new ArrayList<String>();
		SCOPES.add(Oauth2Scopes.USERINFO_EMAIL);
		SCOPES.add(GmailScopes.GMAIL_READONLY);

		AuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.
				Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory(), Constants.CLIENT_ID, Constants.CLIENT_SECRET, SCOPES).
						setCredentialDataStore(StoredCredential.getDefaultDataStore(OauthDataStore.getDataStoreFactory())).
						setAccessType("offline").
						setApprovalPrompt("force").
						build();
		return flow;
	}
}
