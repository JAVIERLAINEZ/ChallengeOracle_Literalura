package com.alura.ChallengeOracle_Literalura.demo.repository;

import com.alura.ChallengeOracle_Literalura.demo.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    /**
     * Busca libros cuyo título contenga el texto proporcionado, sin importar mayúsculas o minúsculas.
     * @param titulo Texto parcial o completo del título del libro.
     * @return Lista de libros que coinciden con el criterio.
     */
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    /**
     * Busca un libro por su título exacto.
     * @param titulo Título exacto del libro.
     * @return El libro si existe.
     */
    Optional<Libro> findByTitulo(String titulo);

    /**
     * Busca libros por idioma, sin importar mayúsculas o minúsculas.
     * @param idioma Idioma del libro.
     * @return Lista de libros en el idioma especificado.
     */
    List<Libro> findByIdiomaIgnoreCase(String idioma);
}
