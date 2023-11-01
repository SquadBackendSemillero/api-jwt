package com.depgirpro.securitytokenlogin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="personas")
public class Persona implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PERSONA")
    private Long id;

    @Column(name="NOMBRE", nullable = false)
    private String nombre;

    @Column(name="N_IDENTIFICACION", unique = true, nullable = false)
    private String documento;
    @Column(name="EDAD", nullable = false)
    private Integer edad;
    @Column(name="PESO")
    private Float peso;
    @Column(name="ALTURA")
    private Float altura;
    @Column(name="CORREO", nullable = false, unique = true)
    private String correo;

    @Column(name="CONTRASENA", nullable = false)
    private String contrasena;


    @Column(name="FECHA_CREACION")
    @CreationTimestamp
    private LocalDateTime createAt;


    @Column(name="FECHA_MODIFICACION")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name="DORSAL", nullable = true)
    private String dorsal;

    @Column(name="FOTO", nullable = true)
    private String foto;


    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="ID_ROL")
    private Rol rol;


    public Persona(String nombre, String documento, Integer edad, Float peso, Float altura, String correo, String contrasena, String dorsal, String foto, Rol rol) {
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.correo = correo;
        this.contrasena = contrasena;
        this.dorsal = dorsal;
        this.foto = foto;
        this.rol = rol;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities=new ArrayList<>();
         authorities.add(new SimpleGrantedAuthority("ROLE_"+this.rol.getNom_rol()));
         return authorities;
    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
