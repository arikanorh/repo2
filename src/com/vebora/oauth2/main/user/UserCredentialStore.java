package com.vebora.oauth2.main.user;

import java.io.IOException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialRefreshListener;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.auth.oauth2.TokenErrorResponse;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.util.Utils;
import com.vebora.oauth2.cache.Constants;
import com.vebora.oauth2.cache.OauthDataStore;

public class UserCredentialStore
{

	public static void setUserCredential(String userId, Credential credential) throws IOException
	{
		StoredCredential.getDefaultDataStore(OauthDataStore.getDataStoreFactory()).set(userId, new StoredCredential(credential));

	}

	public static void removeUserCredential(String userId) throws IOException
	{
		StoredCredential.getDefaultDataStore(OauthDataStore.getDataStoreFactory()).delete(userId);

	}

	public static boolean hasCredentialFor(String userId) throws IOException
	{
		return StoredCredential.getDefaultDataStore(OauthDataStore.getDataStoreFactory()).containsKey(userId);
	}

	public static GoogleCredential getUserCredential(final String userId) throws IOException
	{
		StoredCredential userCred = StoredCredential.getDefaultDataStore(OauthDataStore.getDataStoreFactory()).get(userId);

		String accesToken = userCred.getAccessToken();

		GoogleCredential credential = new GoogleCredential.Builder().
				setTransport(Utils.getDefaultTransport()).
				setJsonFactory(Utils.getDefaultJsonFactory()).
				setClientSecrets(Constants.CLIENT_ID, Constants.CLIENT_SECRET).
				addRefreshListener(new CredentialRefreshListener()
				{

					@Override
					public void onTokenResponse(Credential credential, TokenResponse tokenResponse) throws IOException
					{
						UserCredentialStore.setUserCredential(userId, credential);

					}

					@Override
					public void onTokenErrorResponse(Credential credential, TokenErrorResponse tokenErrorResponse) throws IOException
					{
						UserCredentialStore.removeUserCredential(userId);

					}
				}).
				build();

		credential.setAccessToken(accesToken);
		credential.setRefreshToken(userCred.getRefreshToken());
		return credential;
	}
}
