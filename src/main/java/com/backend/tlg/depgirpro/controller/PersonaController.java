package com.backend.tlg.depgirpro.controller;

import com.backend.tlg.depgirpro.dto.RegistroPersonaDTO;
import com.backend.tlg.depgirpro.services.PersonaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping("/registro")
    public ResponseEntity<?> insertar(@Valid @RequestBody RegistroPersonaDTO dto, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldErrors().stream().map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList()));
        }
        return this.personaService.insertar(dto);

    }
}
