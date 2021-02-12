package com.hashedin.bitly;

public class MappingException extends RuntimeException {

	private static final long serialVersionUID = 6884864411787279259L;
	
	public MappingException(String shortCode) {
		super("Invalid shortCode " + shortCode);
	}

}
