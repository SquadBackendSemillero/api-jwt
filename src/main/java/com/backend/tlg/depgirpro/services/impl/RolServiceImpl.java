package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.PermisoResponseDTO;
import com.backend.tlg.depgirpro.entity.Operacion;
import com.backend.tlg.depgirpro.entity.Permiso;
import com.backend.tlg.depgirpro.entity.Rol;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.repository.OperacionRepository;
import com.backend.tlg.depgirpro.repository.RolRepository;
import com.backend.tlg.depgirpro.services.RolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRep;
    private final OperacionRepository opRep;

    @Override
    public ResponseEntity<PermisoResponseDTO> agregarPermiso(Long idRol, Long idOp) {
        Rol rolBD=this.rolRep.findById(idRol).orElseThrow(()->new NotFoundExceptionManaged("Not found"));
        Operacion opBD=this.opRep.findById(idOp).orElseThrow(()->new NotFoundExceptionManaged("Not found"));
        Permiso permisoNew=new Permiso(rolBD, opBD);
        rolBD.getPermisos().add(permisoNew);
        this.rolRep.save(rolBD);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PermisoResponseDTO(
                permisoNew.getId(),
                rolBD.getId(),
                rolBD.getRol(),
                opBD.getId(),
                opBD.getNombre()));
    }
}
