package com.depgirpro.securitytokenlogin.config.security;

import com.depgirpro.securitytokenlogin.config.security.Filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionMangConfig->sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig->{
                    authConfig.requestMatchers(HttpMethod.POST, "/personas").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    authConfig.requestMatchers("/error").permitAll();

                    authConfig.requestMatchers(HttpMethod.GET, "/roles/{idRol}").hasRole("Jugador");
                    authConfig.requestMatchers(HttpMethod.GET, "/roles").hasRole("Jugador");

                });
        return http.build();
    }

}
