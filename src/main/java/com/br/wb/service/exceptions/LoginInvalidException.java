package com.br.wb.service.exceptions;

import java.io.Serial;

public class LoginInvalidException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;
	
	public LoginInvalidException() {
		super("E-mail or password not found.");
	}
}
