package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.PermisoResponseDTO;
import com.backend.tlg.depgirpro.entity.Operacion;
import com.backend.tlg.depgirpro.entity.Permiso;
import com.backend.tlg.depgirpro.entity.Rol;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.repository.OperacionRepository;
import com.backend.tlg.depgirpro.repository.PermisoRepository;
import com.backend.tlg.depgirpro.repository.RolRepository;
import com.backend.tlg.depgirpro.services.RolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRep;
    private final OperacionRepository opRep;
    private final PermisoRepository permisoRep;

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

    @Override
    public ResponseEntity<?> quitarPermiso(Long idRol, Long idPermiso) {
        Map<String, Object> respuesta=new HashMap<>();
        Rol rolBD=this.rolRep.findById(idRol).orElseThrow(()->new NotFoundExceptionManaged("Not found"));
        Permiso permisoBD=this.permisoRep.findById(idPermiso).orElseThrow(()->new NotFoundExceptionManaged("Not found"));
        if (!rolBD.getPermisos().contains(permisoBD)){
            respuesta.put("mensaje", "Este rol no contiene el permiso indicado");
            return ResponseEntity.badRequest().body(respuesta);
        }
        rolBD.getPermisos().remove(permisoBD);
        this.rolRep.save(rolBD);
        respuesta.put("mensaje", "Permiso '" + permisoBD.getOperacion().getNombre() + "' eliminado correctamente.");
        return ResponseEntity.ok(respuesta);

    }

    @Override
    public ResponseEntity<List<PermisoResponseDTO>> listarOperacionesPermitidas(Long idRol) {
        if (this.rolRep.listarOperacionesPermitidas(idRol).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.rolRep.listarOperacionesPermitidas(idRol));

    }
}
