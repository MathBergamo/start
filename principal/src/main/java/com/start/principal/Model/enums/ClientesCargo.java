package com.start.principal.Model.enums;

import lombok.Getter;

@Getter
public enum ClientesCargo {
    ADMIN("CARGO_ADMIN"),
    USER("CARGO_USER");

    private final String cargo;

    ClientesCargo(String cargo) {
        this.cargo = cargo;
    }
}
