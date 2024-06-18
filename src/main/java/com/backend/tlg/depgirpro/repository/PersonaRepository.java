package com.backend.tlg.depgirpro.repository;

import com.backend.tlg.depgirpro.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByCorreo(String correo);
}
