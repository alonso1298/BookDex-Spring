package com.alonsoSG.BookDex.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "libro_autor", // Talba intermedia
        joinColumns = @JoinColumn(name = "libro_id"), // Foring Key
        inverseJoinColumns = @JoinColumn(name = "autor_id") // foring key autores
    )
    private List<Autor> autor;
    private String idioma;
    private Integer numeroDeDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor().stream()
            .map(Autor::new) // Usar el constructor de Autor que acepta DatosAutor
            .toList();
        this.idioma = datosLibro.idiomas().get(0).trim();
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
    public List<Autor> getAutor() {
        return autor;
    }
    public void setAutores(List<Autor> autor) {
        this.autor = autor;
    }
    public String getIdioma() {
        return idioma;
    }
    public void setIdiomas(String idiomas) {
        this.idioma = idiomas;
    }
    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libro titulo=" + titulo + ", autor=" + autor + ", idiomas=" + idioma + ", numeroDeDescargas="
                + numeroDeDescargas;
    }

    
}


