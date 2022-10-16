package com.unvime.projectapi.exceptions;

import org.springframework.http.HttpStatus;

public class LoginException extends ApiException {
    public LoginException() {
        super("fail_login","El username o la contraseña es equivocada", HttpStatus.BAD_REQUEST.value());
    }
}
