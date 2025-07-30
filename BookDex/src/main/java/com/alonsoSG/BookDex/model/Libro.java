package com.alonsoSG.BookDex.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(cascade = CascadeType.ALL)
    private Autor autor;
    @ElementCollection
    private List<String> idiomas;
    private Integer numeroDeDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        if(!datosLibro.autor().isEmpty()){
            DatosAutor datosAutor = datosLibro.autor().get(0);
            Autor autor = new Autor();
            autor.setNombre(datosAutor.nombre());
            autor.setFechaDeFallecimiento(datosAutor.fechaDeNacimiento());
            autor.setFechaDeNacimiento(datosAutor.fechaDeFallecimiento());
            this.autor = autor;
        }
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
    public Autor getAutor() {
        return autor;
    }
    public void setAutores(Autor autor) {
        this.autor = autor;
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
        return "Libro titulo=" + titulo + ", autor=" + autor + ", idiomas=" + idiomas + ", numeroDeDescargas="
                + numeroDeDescargas;
    }

    
}


