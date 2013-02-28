package net.vksn.ecm.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;

import net.vksn.sitemap.model.SitemapItem;

public class Navigation extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private String var;
	private int depth = 1;
	private int round = 1;
	private SitemapItem sitemapItem;
	private SitemapItem itemToIterate;

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public SitemapItem getSitemapItem() {
		return sitemapItem;
	}

	public void setSitemapItem(SitemapItem sitemapItem) {
		this.sitemapItem = sitemapItem;
	}

	@Override
	public int doStartTag() throws JspException {
		itemToIterate = sitemapItem;
		if(itemToIterate != null) {
			while (itemToIterate.getParent() != null) {
				itemToIterate = itemToIterate.getParent();
			}
			pageContext.setAttribute(var, itemToIterate);
			return BodyTag.EVAL_BODY_BUFFERED;
		}
		return SKIP_BODY;
	}

	@Override
	public int doAfterBody() throws JspException {
		try {
			BodyContent bodyContent = super.getBodyContent();
			String bodyString = bodyContent.getString();
			bodyContent.getEnclosingWriter().print(bodyString);
			bodyContent.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		if (round < depth && !itemToIterate.getChildrens().isEmpty()) {
			itemToIterate = itemToIterate.getChildrens().iterator().next();
			pageContext.setAttribute(var, itemToIterate);
			round++;
			return IterationTag.EVAL_BODY_AGAIN;
		}
		return Tag.SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		itemToIterate = null;
		var = null;
		round = 1;
		return super.doEndTag();
	}

}
