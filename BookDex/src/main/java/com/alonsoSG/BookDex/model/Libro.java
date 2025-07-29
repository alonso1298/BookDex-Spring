package com.alonsoSG.BookDex.model;

import java.util.List;

public class Libro {
    private Long id;
    private String titulo;
    private List<Autores> autores;
    private List<String> idiomas;
    private Integer numeroDeDescargas;
    
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
    public List<Autores> getAutores() {
        return autores;
    }
    public void setAutores(List<Autores> autores) {
        this.autores = autores;
    }
    public List<String> getIdiomas() {
        return idiomas;
    }
    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }
    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    
}


