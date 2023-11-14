package com.depgirpro.securitytokenlogin.service;

import com.depgirpro.securitytokenlogin.dto.LoginRequestDTO;
import com.depgirpro.securitytokenlogin.dto.RegistroPersonaDTO;
import com.depgirpro.securitytokenlogin.model.Persona;
import com.depgirpro.securitytokenlogin.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private PersonaRepository personaRep;

    public Persona login(LoginRequestDTO datos){

        Persona persona=personaRep.findByCorreo(datos.getCorreo()).orElseThrow(()->new RuntimeException("No existe el usuario"));

        if(!persona.getPassword().equals(datos.getContrasena())){
            throw new RuntimeException("Clave incorrecta");
        }

        // retornar un objeto con la persona y el inicio exitoso

        return persona;
    }

    public Persona registro(RegistroPersonaDTO datos){
        Persona persona = new Persona(datos.getNombre(), datos.getIdentificacion(), datos.getEdad(), datos.getPeso(), datos.getAltura(), datos.getCorreo(), datos.getContrasena(), datos.getDorsal());

        personaRep.save(persona);

        return persona;
    }

}
