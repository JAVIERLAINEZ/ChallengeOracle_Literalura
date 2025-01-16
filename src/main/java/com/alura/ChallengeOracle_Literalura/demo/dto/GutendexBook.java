package com.alura.ChallengeOracle_Literalura.demo.dto;
//Los DTOs permiten mapear la estructura JSON de la API en objetos Java.

import java.util.List;

public class GutendexBook {
    private String title;
    private List<GutendexAuthor> authors;
    private List<String> languages;
    private int download_count;

    // Getters y Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GutendexAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<GutendexAuthor> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }
}
