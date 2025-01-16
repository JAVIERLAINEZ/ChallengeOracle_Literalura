package com.alura.ChallengeOracle_Literalura.demo.dto;
//Los DTOs permiten mapear la estructura JSON de la API en objetos Java

import java.util.List;

public class GutendexBookResponse {
    private List<GutendexBook> results;

    // Getters y Setters

    public List<GutendexBook> getResults() {
        return results;
    }

    public void setResults(List<GutendexBook> results) {
        this.results = results;
    }
}

