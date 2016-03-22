package com.vebora.oauth2.cache.services;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;
import com.vebora.oauth2.main.user.UserCredentialStore;

public class GmailService
{

	public static List<Thread> getThreads(String userId) throws IOException
	{

		Gmail gmail = getGmail(userId);

		Gmail.Users.Threads.List list = gmail.users().threads().list("me");

		ListThreadsResponse response = list.execute();

		List<Thread> threads = response.getThreads();
		return threads;
	}

	private static Gmail getGmail(String userId) throws IOException
	{
		GoogleCredential credential = UserCredentialStore.getUserCredential(userId);
		Gmail gmail = new Gmail.Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory(), credential).build();
		return gmail;
	}

	public static Message getMessage(String messageId, String userId) throws IOException
	{
		Gmail gmail = getGmail(userId);

		Gmail.Users.Messages.Get get = gmail.users().messages().get("me", messageId).setFormat("raw");

		Message msg = get.execute();

		return msg;

	}

	public static void getMessages(String userId) throws IOException, MessagingException
	{

		Gmail gmail = getGmail(userId);

		Gmail.Users.Messages.List list = gmail.users().messages().list("me").setMaxResults(32L);

		ListMessagesResponse response = list.execute();

		List<Message> mesages = response.getMessages();

		// for (Message message : mesages)
		// {
		// Message messi = gmail.users().messages().get("me", message.getId()).setFormat("raw").execute();
		// if (messi.getLabelIds().contains("CHAT"))
		// {
		// continue;
		// }
		// byte[] emailBytes = Base64.decodeBase64(messi.getRaw());
		//
		// Properties props = new Properties();
		// Session session = Session.getDefaultInstance(props, null);
		//
		// MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
		//
		// System.out.println(email.getContent());
		//
		// }

	}
}
