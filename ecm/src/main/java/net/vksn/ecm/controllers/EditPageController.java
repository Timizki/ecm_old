package net.vksn.ecm.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.ecm.controllers.form.SitemapItemForm;
import net.vksn.ecm.controllers.validator.SitemapItemFormValidator;
import net.vksn.ecm.converter.SitemapItemEditor;
import net.vksn.ecm.model.TilesDefinition;
import net.vksn.ecm.service.DefinitionService;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditPageController extends AbstractPageController {

	@Autowired
	private SitemapItemService sitemapItemService;

	@Autowired
	private SitemapService sitemapService;

	@Autowired
	private DefinitionService defintionService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SitemapItemEditor sitemapItemEditor = new SitemapItemEditor();
		sitemapItemEditor.setSitemapItempService(sitemapItemService);
		binder.registerCustomEditor(SitemapItem.class, sitemapItemEditor);
	}
	
	private List<TilesDefinition> getDefinitions() {
		List<TilesDefinition> definitions = defintionService.getDefinitions();
		return definitions;
	}
	
	@RequestMapping(params = "mode=edit", method = RequestMethod.GET)
	public String showEditForm(@ModelAttribute SitemapItemForm form) {
		return form.getSitemapItem().getDecorationName();
	}

	@RequestMapping(params = "mode=addPage", method = RequestMethod.GET)
	public String showNewForm(@ModelAttribute SitemapItemForm form) {
		return form.getSitemapItem().getDecorationName();
	}
	
	@ModelAttribute
	public SitemapItemForm getForm(@RequestParam String mode, HttpServletRequest request) throws EntityNotFoundException {
		SitemapItemForm form = new SitemapItemForm();
		int sitemapId = getSitemapId(request);
		String[] path = getPagePath(request);
		SitemapItem item = null;
		
		List<TilesDefinition> templates = getDefinitions();
		if("edit".equals(mode)){
			item = sitemapItemService.getItemByPath(sitemapId, path);
			form.setSiblings(sitemapItemService.getSiblings(item));
		}
		else {
			item = new SitemapItem();
			Sitemap sitemap = sitemapService.getSitemap(sitemapId, false);
			item.setSitemap(sitemap);
			item.setDecorationName(templates.iterator().next().getName());
			form.setSiblings(sitemap.getSitemapItems());
		}
		
		form.setSitemapItem(item);
		form.setSitemapItems(sitemapItemService.getAllSitemapItems(sitemapId));
		form.setTemplates(templates);		
		
		return form;
	}
	

	@RequestMapping(method = RequestMethod.POST)
	public String storePage(@ModelAttribute("sitemapItemForm") SitemapItemForm form, ModelMap model, BindingResult result, HttpServletRequest request)
			throws EntityNotFoundException {
		
		new SitemapItemFormValidator().validate(form, result);
		
		String action = request.getParameter("action");

		if("decorationName".equals(action)) {
			return form.getSitemapItem().getDecorationName();
		}
		else if("parent".equals(action)) {
			Set<SitemapItem> items = sitemapItemService.getSiblings(form.getParent());
			form.setSiblings(items);
			form.getSitemapItem().setParent(form.getParent());
			return form.getSitemapItem().getDecorationName();
		}
		if (result.hasErrors()) {
			return form.getSitemapItem().getDecorationName();
		}
		sitemapItemService.storeSitemapItem(form.getSitemapItem());

		return "redirect:/" + form.getSitemapItem().getPathAsString() + ".html";
	}

}
