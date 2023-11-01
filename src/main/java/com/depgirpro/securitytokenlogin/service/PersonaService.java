package com.depgirpro.securitytokenlogin.service;

import com.depgirpro.securitytokenlogin.dto.RegistroPersonaDTO;
import org.springframework.http.ResponseEntity;

public interface PersonaService {

    ResponseEntity<?> insertar(RegistroPersonaDTO datos);
}
