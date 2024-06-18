package com.backend.tlg.depgirpro.services.impl;

import com.backend.tlg.depgirpro.dto.PersonaResponseDTO;
import com.backend.tlg.depgirpro.dto.RegistroPersonaDTO;
import com.backend.tlg.depgirpro.entity.Equipo;
import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.entity.Rol;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.mapper.PersonaRequestMapper;
import com.backend.tlg.depgirpro.mapper.PersonaResponseMapper;
import com.backend.tlg.depgirpro.repository.EquipoRepository;
import com.backend.tlg.depgirpro.repository.PersonaRepository;
import com.backend.tlg.depgirpro.repository.RolRepository;
import com.backend.tlg.depgirpro.services.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRep;
    private final EquipoRepository equipoRep;
    private final PersonaRequestMapper personaRequestMapper;
    private final PersonaResponseMapper personaResponseMapper;
    private final RolRepository rolRep;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<?> insertar(RegistroPersonaDTO dto) {
        Map<String, Object> respuesta=new HashMap<>();
        if (this.personaRep.findByCorreo(dto.getCorreo()).isPresent()){
            respuesta.put("mensaje", "El correo ingresado ya está siendo utilizado por otra cuenta, coloque otra dirección");
            return ResponseEntity.badRequest().body(respuesta);
        }
        Equipo equipoBD=this.equipoRep.findById(dto.getIdEquipo()).orElseThrow(
                ()->new NotFoundExceptionManaged("404", "Error de búsqueda", "Equipo no encontrado en la base de datos", HttpStatus.NOT_FOUND));
        Rol rolJugador=this.rolRep.findById(2L).get();
        Persona personaNew=this.personaRequestMapper.toPersona(dto);
        personaNew.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        personaNew.setEquipo(equipoBD);
        personaNew.setRole(rolJugador);
        this.personaRep.save(personaNew);
        respuesta.put("persona", this.personaResponseMapper.toPersonaResponseDTO(personaNew));
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
