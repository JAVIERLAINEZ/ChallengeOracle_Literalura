package com.alura.ChallengeOracle_Literalura.demo.client;
//Esta clase gestionará las consultas a la API de Gutendex.

import org.springframework.web.client.RestTemplate;
import com.alura.ChallengeOracle_Literalura.demo.dto.GutendexBookResponse;
import org.springframework.stereotype.Component;


@Component
public class GutendexClient {

    private static final String API_URL = "https://gutendex.com/books?search=";

    private final RestTemplate restTemplate;

    public GutendexClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Método para consultar la API con un título de libro
    public GutendexBookResponse buscarLibroPorTitulo(String titulo) {
        try {
            String url = API_URL + titulo.replace(" ", "%20");
            return restTemplate.getForObject(url, GutendexBookResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la API de Gutendex", e);
        }
    }
}


