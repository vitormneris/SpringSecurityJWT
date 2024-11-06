package com.br.wb.enums;

import lombok.Getter;

@Getter
public enum Values {

    ADMIN("admin"),

    CLIENT("client");

    private final String role;

    Values(String role) {
        this.role = role;
    }
}
