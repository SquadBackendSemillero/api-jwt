package com.depgirpro.securitytokenlogin.controller;

import com.depgirpro.securitytokenlogin.dto.RegistroPersonaDTO;
import com.depgirpro.securitytokenlogin.service.PersonaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<?> listarPersonas(){
        return personaService.listarPersonas();
    }

    @PostMapping
    public ResponseEntity<?> insertar( @RequestBody RegistroPersonaDTO datos){
        return personaService.insertar(datos);
    }

}
