package net.vksn.ecm.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.ecm.controllers.validator.SitemapItemValidator;
import net.vksn.ecm.converter.SitemapItemEditor;
import net.vksn.ecm.model.TilesDefinition;
import net.vksn.ecm.service.DefinitionService;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;
import net.vksn.sitemap.services.SitemapItemService;
import net.vksn.sitemap.services.SitemapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("sitemapItem")
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

	@RequestMapping(params = "mode=edit", method = RequestMethod.GET)
	public ModelAndView getEditPage(HttpServletRequest request)
			throws EntityNotFoundException {
		int sitemapId = getSitemapId(request);
		String[] path = getPagePath(request);
		SitemapItem item = sitemapItemService.getItemByPath(sitemapId, path);

		ModelAndView mv = new ModelAndView(item.getDecorationName());
		mv.addObject("sitemapItem", item);
		return mv;
	}

	@RequestMapping(params = "mode=addPage", method = RequestMethod.GET)
	public ModelAndView getNewPage(HttpServletRequest request)
			throws EntityNotFoundException {
		int sitemapId = getSitemapId(request);
		Sitemap sitemap = sitemapService.getSitemap(sitemapId);
		SitemapItem item = new SitemapItem();
		item.setDecorationName("home");
		List<TilesDefinition> definitions = defintionService.getDefinitions();

		ModelAndView mv = new ModelAndView(item.getDecorationName());
		mv.addObject("sitemapItem", item);
		mv.addObject("sitemap", sitemap);
		mv.addObject("templates", definitions);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String storePage(@ModelAttribute("sitemapItem") SitemapItem form,
			Model model, BindingResult result, HttpServletRequest request)
			throws EntityNotFoundException {
		new SitemapItemValidator().validate(form, result);
		if (result.hasErrors()) {
			return null;
		}
		sitemapItemService.storeSitemapItem(form);

		return "redirect:/" + form.getPathAsString() + ".html";
	}

}
