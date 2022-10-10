package com.unvime.projectapi.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    public NotFoundException() {
        super("not_found","Element not found", HttpStatus.NOT_FOUND.value());
    }
}
