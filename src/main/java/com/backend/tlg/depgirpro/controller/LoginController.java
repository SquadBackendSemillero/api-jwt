package com.backend.tlg.depgirpro.controller;

import com.backend.tlg.depgirpro.dto.LoginRequestDTO;
import com.backend.tlg.depgirpro.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
public class LoginController {

    private


    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO credenciales, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldErrors().stream().map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList()));
        }

    }
}
