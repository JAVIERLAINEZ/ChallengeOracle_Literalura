package com.alura.ChallengeOracle_Literalura.demo.dto;
//Los DTOs permiten mapear la estructura JSON de la API en objetos Java.

public class GutendexAuthor {
    private String name;
    private Integer birth_year;
    private Integer death_year;

    // Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }
}
