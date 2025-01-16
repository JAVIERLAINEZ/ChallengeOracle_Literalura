package com.alura.ChallengeOracle_Literalura.demo.service;

import com.alura.ChallengeOracle_Literalura.demo.entity.Autor;
import com.alura.ChallengeOracle_Literalura.demo.repository.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    // Inyección de dependencia del repositorio de autores
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Método para listar todos los autores con la inicialización de la colección librosPublicados
    @Transactional(readOnly = true)
    public List<Autor> listarTodos() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(autor -> {
            // Inicializar la colección librosPublicados
            autor.getLibrosPublicados().size();
        });
        return autores;
    }

    // Método para listar autores vivos en un año específico, inicializando la colección librosPublicados y manejando valores null
    @Transactional(readOnly = true)
    public List<Autor> listarVivosEnAnio(Integer year) {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(autor -> {
            // Inicializar la colección librosPublicados
            autor.getLibrosPublicados().size();
        });
        return autores.stream()
                .filter(autor -> {
                    Integer fechaNacimiento = autor.getFechaNacimiento();
                    Integer fechaFallecimiento = autor.getFechaFallecimiento();
                    return (fechaNacimiento != null && fechaNacimiento <= year) &&
                            (fechaFallecimiento == null || year < fechaFallecimiento);
                })
                .toList();
    }

    // Método para listar autores únicos con sus libros publicados, inicializando la colección librosPublicados
    @Transactional(readOnly = true)
    public List<Autor> listarAutoresConLibros() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(autor -> {
            // Inicializar la colección librosPublicados
            autor.getLibrosPublicados().size();
        });
        return autores;
    }
}
