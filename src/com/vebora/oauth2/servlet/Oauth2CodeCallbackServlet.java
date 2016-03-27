package com.vebora.oauth2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.GenericUrl;
import com.vebora.oauth2.cache.Cache;
import com.vebora.oauth2.main.user.UserIdSupplier;

@SuppressWarnings("serial")
public class Oauth2CodeCallbackServlet extends AbstractAuthorizationCodeCallbackServlet
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
		return UserIdSupplier.getOrCreateUserIdFromHttpRequest(req);
	}

	@Override
	protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential, String userId) throws ServletException, IOException
	{
		Cookie cook = new Cookie(Cache.USER_ID, userId);
		cook.setPath("/");
		cook.setMaxAge(1 * 1000 * 1000);
		resp.addCookie(cook);
		resp.sendRedirect("/welcome");
	}

	@Override
	protected void onError(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse) throws ServletException, IOException
	{
		System.out.println("Error");
	}
}
