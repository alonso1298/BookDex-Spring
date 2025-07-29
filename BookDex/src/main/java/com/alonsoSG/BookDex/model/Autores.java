package com.alonsoSG.BookDex.model;

public class Autores {
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;

    public Autores(){}

    public Autores(DatosAutor datosAutores){
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

