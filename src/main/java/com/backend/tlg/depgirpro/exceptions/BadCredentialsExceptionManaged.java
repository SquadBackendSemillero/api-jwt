package com.backend.tlg.depgirpro.exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class BadCredentialsExceptionManaged extends BadCredentialsException {
    public BadCredentialsExceptionManaged(String mensaje) {
        super(mensaje);
    }
}
