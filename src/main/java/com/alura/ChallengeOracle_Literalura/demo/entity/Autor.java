package com.alura.ChallengeOracle_Literalura.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    // Usar ElementCollection para almacenar una lista de cadenas
    @ElementCollection
    @CollectionTable(name = "libros_publicados", joinColumns = @JoinColumn(name = "autor_id"))
    @Column(name = "titulo")
    private List<String> librosPublicados = new ArrayList<>(); // Inicialización aquí

    public Autor() {
    }

    public Autor(String nombre, Integer fechaNacimiento, Integer fechaFallecimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.librosPublicados = new ArrayList<>(); // También inicializamos en el constructor
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<String> getLibrosPublicados() {
        return librosPublicados;
    }

    public void setLibrosPublicados(List<String> librosPublicados) {
        this.librosPublicados = librosPublicados;
    }
}
