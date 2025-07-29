package com.alonsoSG.BookDex.model;

import java.util.List;

public class Libro {
    private Long id;
    private String titulo;
    private List<DatosAutores> autores;
    private List<DatosIdioma> idiomas;
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
    public List<DatosAutores> getAutores() {
        return autores;
    }
    public void setAutores(List<DatosAutores> autores) {
        this.autores = autores;
    }
    public List<DatosIdioma> getIdiomas() {
        return idiomas;
    }
    public void setIdiomas(List<DatosIdioma> idiomas) {
        this.idiomas = idiomas;
    }
    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    
}


