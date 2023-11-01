package com.depgirpro.securitytokenlogin.service;

import com.depgirpro.securitytokenlogin.dto.LoginRequestDTO;
import com.depgirpro.securitytokenlogin.dto.TokenResponseDTO;
import com.depgirpro.securitytokenlogin.model.Persona;
import com.depgirpro.securitytokenlogin.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PersonaRepository personaRep;

    @Autowired
    private JWTService jwtService;

    public TokenResponseDTO login(LoginRequestDTO datos){
        UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(datos.getCorreo(), datos.getContrasena());
        authenticationManager.authenticate(upat);
        Persona persona=personaRep.findByCorreo(datos.getCorreo()).get();
        String jwt=jwtService.generarToken(persona, generateExtraClaims(persona));
        return new TokenResponseDTO(jwt);
    }

    private Map<String, Object> generateExtraClaims(Persona persona){
        Map<String, Object> extraClaims=new HashMap<>();
        extraClaims.put("name", persona.getNombre());
        extraClaims.put("rol", persona.getRol().getNom_rol());
        extraClaims.put("permisos", persona.getAuthorities());
        return extraClaims;
    }
}
