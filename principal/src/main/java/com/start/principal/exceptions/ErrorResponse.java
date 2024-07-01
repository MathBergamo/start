package com.start.principal.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

    public ErrorResponse(Integer status, String error, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
