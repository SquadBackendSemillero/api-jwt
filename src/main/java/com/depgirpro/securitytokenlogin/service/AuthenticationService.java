package com.depgirpro.securitytokenlogin.service;

import com.depgirpro.securitytokenlogin.dto.LoginRequestDTO;
import com.depgirpro.securitytokenlogin.dto.RegistroPersonaDTO;
import com.depgirpro.securitytokenlogin.model.Persona;
import com.depgirpro.securitytokenlogin.model.Rol;
import com.depgirpro.securitytokenlogin.repository.PersonaRepository;
import com.depgirpro.securitytokenlogin.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private PersonaRepository personaRep;

    @Autowired
    private RolRepository rolRep;

    public Persona login(LoginRequestDTO datos){

        Persona persona=personaRep.findByCorreo(datos.getCorreo()).orElseThrow(()->new RuntimeException("No existe el usuario"));

        if(!persona.getContrasena().equals(datos.getContrasena())){
            throw new RuntimeException("Clave incorrecta");
        }

        // retornar un objeto con la persona y el inicio exitoso

        return persona;
    }

    public Persona registro(RegistroPersonaDTO datos){

        System.out.println("Rol: " + datos.getRol());


        Rol rolBD = rolRep.findById(datos.getRol()).orElseThrow(()->new RuntimeException("No existe el rol"));

        Persona persona = new Persona(datos.getNombre(), datos.getIdentificacion(), datos.getEdad(), datos.getPeso(), datos.getAltura(), datos.getCorreo(), datos.getContrasena(), datos.getDorsal(), rolBD );

        personaRep.save(persona);

        return persona;
    }

}
