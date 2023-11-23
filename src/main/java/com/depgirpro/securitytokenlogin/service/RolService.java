package com.depgirpro.securitytokenlogin.service;


import org.springframework.http.ResponseEntity;


public interface RolService {

    ResponseEntity<?> listarPersonas(Long idRol);
}
