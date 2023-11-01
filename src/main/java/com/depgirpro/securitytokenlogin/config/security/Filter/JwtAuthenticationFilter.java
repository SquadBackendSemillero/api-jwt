package com.depgirpro.securitytokenlogin.config.security.Filter;

import com.depgirpro.securitytokenlogin.model.Persona;
import com.depgirpro.securitytokenlogin.repository.PersonaRepository;
import com.depgirpro.securitytokenlogin.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
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
        String authHeader=request.getHeader("Authorization");
        if (authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String[] authArray=authHeader.split(" ");
        String jwt=authArray[1];
        String correo=jwtService.extractCorreo(jwt);
        Persona persona=personaRep.findByCorreo(correo).get();
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                correo, null, persona.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request,response);
    }
}
