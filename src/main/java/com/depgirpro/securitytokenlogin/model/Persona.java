package com.depgirpro.securitytokenlogin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="personas")
public class Persona {

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


    @ManyToOne( optional = false)
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
        this.rol  = rol;
        this.foto = foto;
    }

    public Persona(String nombre, String documento, Integer edad, Float peso, Float altura, String correo, String contrasena, String dorsal, Rol rol) {
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.correo = correo;
        this.contrasena = contrasena;
        this.dorsal = dorsal;
        this.rol  = rol;
    }

}
