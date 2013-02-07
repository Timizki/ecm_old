package net.vksn.ecm.controllers;

import javax.servlet.http.HttpServletRequest;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.ecm.controllers.form.PageForm;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EditPageController extends AbstractPageController {
	@Autowired
	private SitemapItemService sitemapItemService;
	@Autowired
	private SitemapService sitemapService;
	
	@RequestMapping(params ="edit=true", method=RequestMethod.GET, value="/*.html")
	public void getPageForm(Model model, HttpServletRequest request) throws EntityNotFoundException {
		PageForm form = new PageForm();
		int sitemapId = getSitemapId(request);			
		String[] path = getPagePath(request);			
		SitemapItem item = sitemapItemService.getItemByPath(sitemapId, path);
		form.setSitemapItem(item);
		
		model.addAttribute("sitemapItem", item);		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String storePage(@ModelAttribute("sitemapItem") SitemapItem form,
		    Model model, BindingResult result) throws EntityNotFoundException {
		sitemapItemService.storeSitemapItem(form);
		return "home";
	}
			
}