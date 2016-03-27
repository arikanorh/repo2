package com.vebora.oauth2.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

@SuppressWarnings("serial")
public class Serve extends HttpServlet
{
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException
	{
		// res.setContentType("text/html");

		BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));

		blobstoreService.serve(blobKey, res);

		// ServingUrlOptions opts = ServingUrlOptions.Builder.withBlobKey(blobKey).crop(true);
		// String url = ImagesServiceFactory.getImagesService().getServingUrl(opts);
		//
		// res.getWriter().println("<img src='" + url + "'  />");

	}
}
