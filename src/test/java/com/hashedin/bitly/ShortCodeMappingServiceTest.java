package com.hashedin.bitly;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

class ShortCodeMappingServiceTest {

	@Test
	void testValidMapping() throws IOException, URISyntaxException {
		Resource resource = new ClassPathResource("valid-mapping.properties");
		ShortCodeMappingService service = new ShortCodeMappingService(resource);
		
		assertEquals(service.getMappedUri("gg"), new URI("https://google.com"));
	}
	
	@Test
	void testBadMappingFile() throws IOException {
		Resource resource = new ClassPathResource("bad-mapping.properties");
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> {
			new ShortCodeMappingService(resource);
		});
		assertTrue(iae.getMessage().contains("Invalid URI"));
	}

}
