package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.dto.PermisoResponseDTO;
import org.springframework.http.ResponseEntity;

public interface RolService {


    ResponseEntity<PermisoResponseDTO> agregarPermiso(Long idRol, Long idOp);

}
