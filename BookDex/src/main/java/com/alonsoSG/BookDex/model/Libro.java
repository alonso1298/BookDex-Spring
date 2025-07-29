package com.alonsoSG.BookDex.model;

import java.util.List;
import java.util.stream.Collectors;

public class Libro {
    private Long id;
    private String titulo;
    private List<Autores> autores;
    private List<String> idiomas;
    private Integer numeroDeDescargas;

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autor().stream()
            .map(datosAutor -> {
                Autores autor = new Autores();
                autor.setNombre(datosAutor.nombre());
                autor.setFechaDeFallecimiento(datosAutor.fechaDeNacimiento());
                autor.setFechaDeFallecimiento(datosAutor.fechaDeFallecimiento());
                return autor;
            })
            .collect(Collectors.toList());
        this.idiomas = datosLibro.idiomas();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }
    
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

    @Override
    public String toString() {
        return "Libro titulo=" + titulo + ", autores=" + autores + ", idiomas=" + idiomas + ", numeroDeDescargas="
                + numeroDeDescargas;
    }

    
}


