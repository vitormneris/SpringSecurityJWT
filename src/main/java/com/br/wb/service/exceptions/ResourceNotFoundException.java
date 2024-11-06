package com.br.wb.service.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String data, Object id) {
		super("Resource not found. " + data + " " + id);
	}
}
