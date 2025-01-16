package com.alura.ChallengeOracle_Literalura.demo.controller;

import com.alura.ChallengeOracle_Literalura.demo.entity.Autor;
import com.alura.ChallengeOracle_Literalura.demo.entity.Libro;
import com.alura.ChallengeOracle_Literalura.demo.service.AutorService;
import com.alura.ChallengeOracle_Literalura.demo.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class LiteraluraConsoleController implements CommandLineRunner {
    private final LibroService libroService;
    private final AutorService autorService;

    // Inyección de dependencias de los servicios de libros y autores
    public LiteraluraConsoleController(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        // Menú principal que se muestra en bucle
        while (true) {
            mostrarMenu();
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                switch (opcion) {
                    case 1 -> buscarLibro(scanner);
                    case 2 -> listarLibros();
                    case 3 -> listarAutores();
                    case 4 -> listarAutoresVivos(scanner);
                    case 5 -> listarLibrosPorIdioma(scanner);
                    case 0 -> {
                        System.out.println("La aplicación se ha detenido");
                        System.exit(0);
                    }
                    default -> System.out.println("Por favor seleccione una opción válida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next(); // Limpiar la entrada no válida
            }
        }
    }

    // Método para mostrar el menú principal
    private void mostrarMenu() {
        System.out.println("Elija la opción a través de su número:");
        System.out.println("1- Buscar libro por título");
        System.out.println("2- Listar libros registrados en la DB");
        System.out.println("3- Listar autores registrados en la DB");
        System.out.println("4- Listar autores vivos en un determinado año");
        System.out.println("5- Listar libros disponibles por idioma");
        System.out.println("0- Salir");
    }

    // Método para buscar y registrar un libro ingresado por el usuario
    private void buscarLibro(Scanner scanner) {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        String titulo = scanner.nextLine(); // Captura el título ingresado por el usuario
        String resultado = libroService.buscarYRegistrarLibro(titulo); // Llama al servicio para buscar y registrar el libro
        System.out.println(resultado); // Imprime el mensaje resultante
    }

    // Método para listar todos los libros registrados
    private void listarLibros() {
        libroService.listarTodos().forEach(libro -> System.out.println(String.format("""
        ----- LIBRO -----
        Título: %s
        Autor: %s
        Idioma: %s
        Numero de descargas: %d
        --------------------
        """, libro.getTitulo(), libro.getNombreAutor(), libro.getIdioma(), libro.getNumeroDescargas())));
    }

    // Método para listar todos los autores registrados y sus libros
    private void listarAutores() {
        autorService.listarAutoresConLibros().forEach(autor -> System.out.println(String.format("""
        Autor: %s
        Fecha de nacimiento: %d
        Fecha de fallecimiento: %d
        Libros: %s
        ----------------------------------------
        """, autor.getNombre(), autor.getFechaNacimiento(), autor.getFechaFallecimiento(), autor.getLibrosPublicados())));
    }

    // Método para listar autores vivos en un año específico ingresado por el usuario
    private void listarAutoresVivos(Scanner scanner) {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar:");
        try {
            int year = scanner.nextInt();
            if (year < 0) {
                System.out.println("El valor ingresado no es un año");
            } else {
                List<Autor> autores = autorService.listarVivosEnAnio(year);
                if (autores.isEmpty()) {
                    System.out.println("No existen autores registrados para el año ingresado: " + year);
                } else {
                    autores.forEach(autor -> System.out.println(String.format("""
                    Autor: %s
                    Fecha de nacimiento: %d
                    Fecha de fallecimiento: %d
                    Libros: %s
                    ----------------------------------------
                    """, autor.getNombre(), autor.getFechaNacimiento(), autor.getFechaFallecimiento(), autor.getLibrosPublicados())));
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("El valor ingresado no es un año");
            scanner.next(); // Limpiar la entrada no válida
        }
    }

    // Método para listar libros por idioma seleccionado por el usuario
    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.println("Ingrese el idioma para buscar libros:");
        System.out.println("ES- español");
        System.out.println("EN- inglés");
        System.out.println("FR- francés");
        System.out.println("PT- portugués");

        String idioma = scanner.nextLine().toUpperCase(); // Captura el idioma ingresado por el usuario y lo convierte a mayúsculas

        if (!List.of("ES", "EN", "FR", "PT").contains(idioma)) {
            System.out.println("La opción ingresada no es válida");
            return;
        }

        List<Libro> libros = libroService.buscarPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros para el idioma ingresado: " + idioma);
        } else {
            libros.forEach(libro -> System.out.println(String.format("""
            ----- LIBRO -----
            Título: %s
            Autor: %s
            Idioma: %s
            Numero de descargas: %d
            --------------------
            """, libro.getTitulo(), libro.getNombreAutor(), libro.getIdioma(), libro.getNumeroDescargas())));
        }
    }
}
