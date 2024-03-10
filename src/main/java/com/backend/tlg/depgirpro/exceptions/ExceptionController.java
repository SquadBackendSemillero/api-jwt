package com.backend.tlg.depgirpro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundExceptionManaged.class)
    public ResponseEntity<String> returnNotFoundException(NotFoundExceptionManaged e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InternalServerExceptionManaged.class)
    public ResponseEntity<String> returnInternalServerException(InternalServerExceptionManaged e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(BadCredentialsExceptionManaged.class)
    public ResponseEntity<String> returnBadCredentialsException(BadCredentialsExceptionManaged e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UserDisabledExceptionManaged.class)
    public ResponseEntity<String> returnDisabledException(UserDisabledExceptionManaged e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedHandlerException(AccessDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No posee los permisos para realizar esta acción");
    }

}
