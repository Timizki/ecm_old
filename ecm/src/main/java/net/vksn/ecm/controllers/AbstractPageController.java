package net.vksn.ecm.controllers;

import javax.servlet.http.HttpServletRequest;

import net.vksn.ecm.filters.SitemapFilter;

public abstract class AbstractPageController {
	protected String[] getPagePath(HttpServletRequest request) {
		String path = request.getServletPath();	
		path = path.substring(1, path.lastIndexOf("."));
		String[] pathSlices = path.split("/");
		return pathSlices;
	}
	
	protected int getSitemapId(HttpServletRequest request) {
		return (Integer)request.getSession().getAttribute(SitemapFilter.CURRENT_SITEMAP);
	}
}
