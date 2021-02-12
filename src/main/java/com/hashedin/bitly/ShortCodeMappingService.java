package com.hashedin.bitly;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ShortCodeMappingService {

	private final Map<String, URI> mapping;
	
	public ShortCodeMappingService(@Value("classpath:mapping.properties") Resource mappingResource) throws IOException {
		this.mapping = loadMapping(mappingResource);
	}
	
	private static final Map<String, URI> loadMapping(Resource mappingResource) throws IOException {
		Properties props = new Properties();
		try(InputStream in = mappingResource.getInputStream()) {
			props.load(mappingResource.getInputStream());			
		}
		
		Map<String, URI> mapping = new HashMap<String, URI>();
		
		for (Entry<Object, Object> entry : props.entrySet()) {
			try {
				String key = entry.getKey().toString();
				URI uri = new URI(entry.getValue().toString());
				validateUri(uri);
				mapping.put(key, uri);
			}catch (URISyntaxException e) {
				// Cannot recover from this exception, it is invalid data
				// So let Spring stop the application server
				throw new IllegalArgumentException("Invalid URL - " + entry.getValue() + " for shortcode " + entry.getKey(), e);
			}
		}
		return Collections.unmodifiableMap(mapping);
	}

	private static void validateUri(URI uri) {
		if ("http".equals(uri.getScheme()) || "https".equals(uri.getScheme())) {
			return;
		} 
		throw new IllegalArgumentException("Invalid URI, expected http or https " + uri);
	}

	public URI getMappedUri(String shortCode) {
		if (mapping.containsKey(shortCode)) {
			return mapping.get(shortCode);
		} else {
			throw new MappingException(shortCode);
		}
	}
}
