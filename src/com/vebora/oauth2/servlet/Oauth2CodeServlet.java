package com.vebora.oauth2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.http.GenericUrl;
import com.vebora.oauth2.main.user.UserIdSupplier;

@SuppressWarnings("serial")
public class Oauth2CodeServlet extends AbstractAuthorizationCodeServlet
{

	@Override
	protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException
	{

		return OauthAuthorizationFlow.newFlow();
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException
	{
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback/");
		return url.build();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException
	{

		String userId = UserIdSupplier.getUserIdFromHttpRequest(req);
		if (userId == null)
		{
			userId = "unauthorized";
		}
		return userId;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.sendRedirect("/welcome");
	}

}
