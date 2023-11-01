package com.depgirpro.securitytokenlogin.service;

import com.depgirpro.securitytokenlogin.dto.PersonaResponseDTO;
import com.depgirpro.securitytokenlogin.model.Rol;
import com.depgirpro.securitytokenlogin.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RolServiceImpl implements RolService{

    private final RolRepository rolRep;
    @Override
    public ResponseEntity<?> listarPersonas(Long idRol) {
        Optional<Rol> rolFound=rolRep.findById(idRol);
        if (!rolFound.isPresent()){
            return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
        }
        List<PersonaResponseDTO> personas=rolFound.get().getPersonas().stream()
                .map(persona->{
                    return new PersonaResponseDTO(persona.getId(), persona.getNombre());
                }).collect(Collectors.toList());
        return ResponseEntity.ok(personas);
    }
}
