package com.alonsoSG.BookDex.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libros;

    public Autor(){}

    public Autor(DatosAutor datosAutores){
        this.nombre = datosAutores.nombre();
        this.fechaDeNacimiento = datosAutores.fechaDeNacimiento();
        this.fechaDeFallecimiento = datosAutores.fechaDeFallecimiento();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }
    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    @Override
    public String toString() {
        return "Autores nombre=" + nombre + ", fechaDeNacimiento=" + fechaDeNacimiento + ", fechaDeFallecimiento="
                + fechaDeFallecimiento;
    }

    
    
}

