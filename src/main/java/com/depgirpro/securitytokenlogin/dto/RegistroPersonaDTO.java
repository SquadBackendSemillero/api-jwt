package com.depgirpro.securitytokenlogin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistroPersonaDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String identificacion;
    @NotNull
    private Integer edad;
    @NotNull
    private Float peso;
    @NotNull
    private Float altura;
    @NotBlank
    private String correo;
    @NotBlank
    private String contrasena;

    private String dorsal;
    private String foto;
    @NotNull
    private Long rol;
}
