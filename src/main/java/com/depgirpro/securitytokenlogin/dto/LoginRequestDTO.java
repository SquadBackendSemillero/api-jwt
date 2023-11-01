package com.depgirpro.securitytokenlogin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    private String correo;
    @NotBlank
    private String contrasena;
}
