package com.hashedin.bitly;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UrlShortenerController {

	private final ShortCodeMappingService service;
	
	@Autowired
	public UrlShortenerController(ShortCodeMappingService service) {
		this.service = service;
	}
	
	@GetMapping("/{shortCode}")
	public void redirector(@PathVariable("shortCode") String shortCode, HttpServletResponse response) throws IOException {
		try {
			URI uri = service.getMappedUri(shortCode);
			response.sendRedirect(uri.toString());			
		} catch (MappingException me) {
			response.setStatus(404);
		}
	}
}
