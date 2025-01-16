package com.alura.ChallengeOracle_Literalura.demo.repository;

import com.alura.ChallengeOracle_Literalura.demo.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repositorio JPA para manejar la entidad Autor
public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Buscar autores nacidos antes de un año específico
    List<Autor> findByFechaNacimientoLessThan(Integer year);
}
