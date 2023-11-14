package com.depgirpro.securitytokenlogin.controller;

import com.depgirpro.securitytokenlogin.dto.LoginRequestDTO;
import com.depgirpro.securitytokenlogin.dto.RegistroPersonaDTO;
import com.depgirpro.securitytokenlogin.model.Persona;
import com.depgirpro.securitytokenlogin.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "register")
public class RegistroController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<Persona> register(@RequestBody RegistroPersonaDTO datos){

        return ResponseEntity.ok(authenticationService.registro(datos));

    }
}
