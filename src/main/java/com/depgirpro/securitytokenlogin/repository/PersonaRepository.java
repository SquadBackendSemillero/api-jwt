package com.depgirpro.securitytokenlogin.repository;

import com.depgirpro.securitytokenlogin.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByCorreo(String correo);

    Optional<Persona> findByDocumento(String documento);
}
