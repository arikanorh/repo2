package com.vebora.oauth2.cache.services;

import java.io.IOException;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.SubscriptionListResponse;
import com.vebora.oauth2.main.user.UserCredentialStore;

public class YoutubeService
{

	private static final Long NUMBER_OF_VIDEOS_RETURNED = 25L;

	public YoutubeService()
	{

	}

	public static void getSomething(String userId) throws IOException
	{
		GoogleCredential credential = UserCredentialStore.getUserCredential(userId);

		YouTube youTube = new YouTube.Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory(), credential).build();

		YouTube.Subscriptions.List search = youTube.subscriptions().list("snippet");

		// To increase efficiency, only retrieve the fields that the
		// application uses.
		search.setMine(true);
		// search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
		search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
		SubscriptionListResponse searchResponse = search.execute();
		List<Subscription> searchResultList = searchResponse.getItems();

		System.out.println(searchResultList);

	}

}
