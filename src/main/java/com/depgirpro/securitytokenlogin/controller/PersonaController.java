package com.depgirpro.securitytokenlogin.controller;

import com.depgirpro.securitytokenlogin.dto.RegistroPersonaDTO;
import com.depgirpro.securitytokenlogin.service.PersonaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<?> insertar(@Valid @RequestBody RegistroPersonaDTO datos){
        return personaService.insertar(datos);
    }

}
