package com.vebora.oauth2.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.model.Thread;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.vebora.oauth2.cache.services.GmailService;
import com.vebora.oauth2.cache.services.GoogleUserInfoService;
import com.vebora.oauth2.main.user.UserIdSupplier;

@SuppressWarnings("serial")
public class WelcomeServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-16LE");
		String userId = UserIdSupplier.getUserIdFromHttpRequest(req);
		Userinfoplus info = null;
		try
		{
			info = GoogleUserInfoService.getUserInfo(userId);

		} catch (GoogleJsonResponseException e)
		{
			resp.getWriter().println("Something is wrong :( Code:" + e.getDetails().getCode() + " " + e.getDetails().getMessage());
			return;
		}

		resp.getWriter().println("<img src='" + info.getPicture() + "' width=50px height:50px' />");
		resp.getWriter().println("Welcome :" + info.getName().toUpperCase());
		resp.getWriter().println("<br>");

		List<Thread> threads = GmailService.getThreads(userId);

		for (Thread thread : threads)
		{
			resp.getWriter().println(" - " + thread.toPrettyString() + "<br>");
		}

	}
}
