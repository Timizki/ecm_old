package net.vksn.ecm.cpanel.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourceController {

	@RequestMapping(value = "/resource_files/{fileName}")
	public HttpEntity<byte[]> getFile(
			@PathVariable(value = "fileName") String fileName) {
		byte[] documentBody = null;

		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "pdf"));
		header.set("Content-Disposition",
				"attachment; filename=" + fileName.replace(" ", "_"));
		header.setContentLength(documentBody.length);

		return new HttpEntity<byte[]>(documentBody, header);
	}
}
