package com.depgirpro.securitytokenlogin.service;

import com.depgirpro.securitytokenlogin.dto.PersonaResponseDTO2;
import com.depgirpro.securitytokenlogin.dto.RegistroPersonaDTO;
import com.depgirpro.securitytokenlogin.model.Persona;
import com.depgirpro.securitytokenlogin.model.Rol;
import com.depgirpro.securitytokenlogin.repository.PersonaRepository;
import com.depgirpro.securitytokenlogin.repository.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonaServiceImpl implements PersonaService{

    private final PersonaRepository personaRep;
    private final RolRepository rolRep;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> insertar(RegistroPersonaDTO datos) {
        Optional<Persona> documentoYaExistente=personaRep.findByDocumento(datos.getIdentificacion());
        if (documentoYaExistente.isPresent()){
            return ResponseEntity.badRequest().body("Persona ya registrada con este N. de documento");
        }
        Optional<Persona> correoYaExistente=personaRep.findByCorreo(datos.getCorreo());
        if (correoYaExistente.isPresent()){
            return ResponseEntity.badRequest().body("Persona ya registrada con este correo");
        }
        Optional<Rol> rolFound=rolRep.findById(datos.getRol());
        if (!rolFound.isPresent()){
            return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
        }
        Rol rolBD=rolFound.get();
        String passwordEncoded=passwordEncoder.encode(datos.getContrasena());
        Persona personaNew=new Persona(datos.getNombre(),datos.getIdentificacion(), datos.getEdad(), datos.getPeso(),datos.getAltura(),datos.getCorreo(),passwordEncoded,(datos.getDorsal()!=null)?datos.getDorsal():"No Disponible", (datos.getFoto()!=null)?datos.getFoto():"No Disponible", rolBD);
        personaRep.save(personaNew);
        PersonaResponseDTO2 response=new PersonaResponseDTO2(personaNew.getId(),personaNew.getNombre(),personaNew.getDocumento(),personaNew.getEdad(),personaNew.getPeso(),personaNew.getAltura(),personaNew.getCorreo(),personaNew.getDorsal(), personaNew.getFoto(),personaNew.getRol());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
