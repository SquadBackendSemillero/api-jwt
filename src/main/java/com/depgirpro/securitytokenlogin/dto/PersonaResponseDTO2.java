package com.depgirpro.securitytokenlogin.dto;


import com.depgirpro.securitytokenlogin.model.Rol;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaResponseDTO2 {

    private Long id;

    private String nombre;

    private String documento;

    private Integer edad;

    private Float peso;

    private Float altura;

    private String correo;

    private String dorsal;


    private String foto;


    private Rol rol;

}

