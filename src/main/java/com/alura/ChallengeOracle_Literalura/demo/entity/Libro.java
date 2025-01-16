package com.alura.ChallengeOracle_Literalura.demo.entity;

import jakarta.persistence.*;

// Entidad que representa los libros en la base de datos
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)  // Asegura unicidad en la columna 'titulo'
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    @Column(nullable = false)
    private String idioma;

    @Column(nullable = false)
    private Integer numeroDescargas;

    @Column(nullable = false)
    private String nombreAutor;

    // Constructor predeterminado
    public Libro() {
    }

    // Constructor completo actualizado
    public Libro(String titulo, Autor autor, String idioma, Integer numeroDescargas, String nombreAutor) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDescargas = numeroDescargas;
        this.nombreAutor = nombreAutor;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
}
