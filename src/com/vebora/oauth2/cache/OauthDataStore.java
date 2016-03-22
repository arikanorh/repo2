package com.vebora.oauth2.cache;

import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.util.store.DataStoreFactory;

public class OauthDataStore
{

	public static DataStoreFactory getDataStoreFactory()
	{
		return AppEngineDataStoreFactory.getDefaultInstance();
	}

}
