package com.depgirpro.securitytokenlogin.service;

import com.depgirpro.securitytokenlogin.dto.PersonaResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RolService {

    ResponseEntity<?> listarPersonas(Long idRol);
}
