package com.backend.tlg.depgirpro.mapper;

import com.backend.tlg.depgirpro.dto.PersonaResponseDTO;
import com.backend.tlg.depgirpro.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PersonaResponseMapper {

    @Mappings({
            @Mapping(source = "id", target = "idPersona"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "edad", target = "edad"),
            @Mapping(source = "peso", target = "peso"),
            @Mapping(source = "altura", target = "altura"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "dorsal", target = "dorsal"),
            @Mapping(source = "equipo.nombre", target = "nombreEquipo")
    })
    PersonaResponseDTO toPersonaResponseDTO(Persona persona);
}
