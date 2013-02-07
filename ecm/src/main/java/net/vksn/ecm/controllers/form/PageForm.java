package net.vksn.ecm.controllers.form;

import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;

public class PageForm {
	private SitemapItem sitemapItem;
	private Sitemap sitemap;

	public SitemapItem getSitemapItem() {
		return sitemapItem;
	}

	public void setSitemapItem(SitemapItem sitemapItem) {
		this.sitemapItem = sitemapItem;
	}

	public Sitemap getSitemap() {
		return sitemap;
	}

	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}

}
