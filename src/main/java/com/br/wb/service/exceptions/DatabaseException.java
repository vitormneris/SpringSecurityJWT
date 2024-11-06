package com.br.wb.service.exceptions;

import java.io.Serial;

public class DatabaseException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String data) {
		super(data);
	}
}
