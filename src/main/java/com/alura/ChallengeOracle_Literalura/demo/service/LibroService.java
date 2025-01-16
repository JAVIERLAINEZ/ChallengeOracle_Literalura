package com.alura.ChallengeOracle_Literalura.demo.service;

import com.alura.ChallengeOracle_Literalura.demo.client.GutendexClient;
import com.alura.ChallengeOracle_Literalura.demo.dto.GutendexAuthor;
import com.alura.ChallengeOracle_Literalura.demo.dto.GutendexBook;
import com.alura.ChallengeOracle_Literalura.demo.dto.GutendexBookResponse;
import com.alura.ChallengeOracle_Literalura.demo.entity.Autor;
import com.alura.ChallengeOracle_Literalura.demo.entity.Libro;
import com.alura.ChallengeOracle_Literalura.demo.repository.AutorRepository;
import com.alura.ChallengeOracle_Literalura.demo.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final GutendexClient gutendexClient;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository, GutendexClient gutendexClient) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.gutendexClient = gutendexClient;
    }

    // Método para validar el título del libro
    private boolean esTituloValido(String titulo) {
        // Define un patrón para títulos válidos (solo letras y números, sin caracteres especiales)
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$");
        return pattern.matcher(titulo).matches();
    }

    /**
     * Busca un libro por título en la API de Gutendex y lo registra en la base de datos.
     * Si el libro ya existe o no se encuentra, devuelve un mensaje apropiado.
     */
    @Transactional
    public String buscarYRegistrarLibro(String titulo) {
        // Validar el título
        if (!esTituloValido(titulo)) {
            return "El título del libro contiene caracteres no válidos.";
        }

        // Convertir el título a minúsculas para la verificación
        String tituloLowerCase = titulo.toLowerCase();

        // Verificar si el libro ya existe en la base de datos por título en minúsculas
        List<Libro> librosExistentes = libroRepository.findByTituloContainingIgnoreCase(tituloLowerCase);
        if (!librosExistentes.isEmpty()) {
            return "No se puede registrar el mismo libro más de una vez";
        }

        // Consultar el libro en la API
        GutendexBookResponse response = gutendexClient.buscarLibroPorTitulo(titulo);
        if (response.getResults().isEmpty()) {
            return "El libro no ha podido ser encontrado";
        }

        // Procesar la información del libro
        GutendexBook apiLibro = response.getResults().get(0);
        String idioma = apiLibro.getLanguages().isEmpty() ? "Desconocido" : apiLibro.getLanguages().get(0);

        // Guardar el autor en la base de datos
        GutendexAuthor apiAutor = apiLibro.getAuthors().get(0);

        // Buscar si el autor ya existe
        Autor autor = autorRepository.findAll().stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(apiAutor.getName()))
                .findFirst()
                .orElseGet(() -> {
                    // Si no existe, crear uno nuevo
                    Autor nuevoAutor = new Autor(apiAutor.getName(), apiAutor.getBirth_year(), apiAutor.getDeath_year());
                    nuevoAutor.setLibrosPublicados(new ArrayList<>()); // Inicializar la lista de libros publicados
                    autorRepository.save(nuevoAutor);
                    return nuevoAutor;
                });

        // Verificar si la lista de libros publicados está inicializada
        if (autor.getLibrosPublicados() == null) {
            autor.setLibrosPublicados(new ArrayList<>());
        }

        // Agregar el título del libro a la lista de libros publicados por el autor, evitando duplicados
        if (!autor.getLibrosPublicados().contains(apiLibro.getTitle())) {
            autor.getLibrosPublicados().add(apiLibro.getTitle());
            autorRepository.save(autor); // Actualizar el autor con el nuevo libro
        }

        // Guardar el libro en la base de datos
        Libro libro = new Libro(
                apiLibro.getTitle(),          // Título
                autor,                        // Objeto Autor
                idioma,                       // Idioma
                apiLibro.getDownload_count(), // Número de descargas
                apiAutor.getName()            // Nombre del autor
        );

        try {
            libroRepository.save(libro);
        } catch (Exception e) {
            return "Error al guardar el libro: " + e.getMessage();
        }

        // Devolver el mensaje formateado
        return String.format("""
            ----- LIBRO -----
            Título: %s
            Autor: %s
            Idioma: %s
            Numero de descargas: %d
            --------------------
        """, libro.getTitulo(), autor.getNombre(), idioma, libro.getNumeroDescargas());
    }

    /**
     * Lista todos los libros registrados en la base de datos.
     */
    @Transactional(readOnly = true)
    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    /**
     * Busca libros por idioma.
     * @param idioma Idioma del libro (e.g., "ES", "EN").
     */
    @Transactional(readOnly = true)
    public List<Libro> buscarPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }
}
