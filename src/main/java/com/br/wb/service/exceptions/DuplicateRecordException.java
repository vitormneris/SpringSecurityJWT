package com.br.wb.service.exceptions;

import java.io.Serial;

public class DuplicateRecordException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateRecordException(String data) {
        super(data);
    }
}
