package com.start.principal.Model.enums;

import lombok.Getter;

@Getter
public enum ClientesCargo {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String role;

    ClientesCargo(String role) {
        this.role = role;
    }
}
