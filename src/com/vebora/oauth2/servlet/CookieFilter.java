package com.vebora.oauth2.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vebora.oauth2.main.user.UserCredentialStore;
import com.vebora.oauth2.main.user.UserIdSupplier;

public class CookieFilter implements Filter
{

	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter) throws IOException, ServletException
	{

		if (req instanceof HttpServletRequest)
		{
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;

			String path = request.getServletPath();
			if (!path.startsWith("/oauth2"))
			{
				String userId = UserIdSupplier.getUserIdFromHttpRequest(request);
				if (userId == null || !UserCredentialStore.hasCredentialFor(userId))
				{
					response.sendRedirect("/oauth2main");
					return;
				} else
				{

				}
			}

		}
		filter.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
