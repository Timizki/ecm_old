package net.vksn.ecm.controllers.validator;

import net.vksn.sitemap.model.SitemapItem;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class SitemapItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(SitemapItem.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SitemapItem item = (SitemapItem)target;
		if( item.getParent() == null) {
			if(item.getSitemap() == null) {
				errors.rejectValue("sitemap", "required");
			}
			else {
				errors.rejectValue("parent", "required");
			}
		}
	}

}
