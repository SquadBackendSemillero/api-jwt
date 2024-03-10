package com.backend.tlg.depgirpro.config.security.filter;

import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.repository.PersonaRepository;
import com.backend.tlg.depgirpro.services.auth.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private PersonaRepository personaRep;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");
        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        String jwt=header.split(" ")[1];
        String correo=this.jwtService.extractUsername(jwt);
        Persona personaBD=this.personaRep.findByCorreo(correo).orElseThrow(()->
                new NotFoundExceptionManaged("Persona no encontrada"));
        UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken
                (correo, null, personaBD.getAuthorities());
        upat.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(upat);
        filterChain.doFilter(request, response);

    }
}
