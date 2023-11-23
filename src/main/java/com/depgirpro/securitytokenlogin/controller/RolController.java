package com.depgirpro.securitytokenlogin.controller;

import com.depgirpro.securitytokenlogin.model.Rol;
import com.depgirpro.securitytokenlogin.repository.RolRepository;
import com.depgirpro.securitytokenlogin.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @Autowired
    private RolRepository rolRep;

    @GetMapping(value = "/{idRol}")
    public ResponseEntity<?> listarPersonas(@PathVariable Long idRol){
        return rolService.listarPersonas(idRol);
    }

    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles(){
        return ResponseEntity.ok(this.rolRep.findAll());
    }

    @PostMapping()
    public ResponseEntity<?> insertarRol(@RequestBody Rol rol){
        rolRep.save(rol);
        return ResponseEntity.ok("Rol insertado");

    }

}
