package com.br.wb.service.exceptions;

import java.io.Serial;

public class InvalidCpfException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidCpfException() {
        super("The CPF is not valid.");
    }
}
