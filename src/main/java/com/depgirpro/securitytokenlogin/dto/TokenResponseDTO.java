package com.depgirpro.securitytokenlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO {
    @Getter @Setter
    private String JWTToken;
}
