package com.backend.tlg.depgirpro.exceptions;

import org.springframework.security.authentication.DisabledException;

public class UserDisabledExceptionManaged extends DisabledException {
    public UserDisabledExceptionManaged(String msg) {
        super(msg);
    }
}
