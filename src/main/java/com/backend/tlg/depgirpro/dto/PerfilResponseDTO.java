package com.backend.tlg.depgirpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponseDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String equipo;
    private String role;

}
