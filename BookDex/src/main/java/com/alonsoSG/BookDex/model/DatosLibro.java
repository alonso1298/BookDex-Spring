package com.alonsoSG.BookDex.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Va a ignorar aquellos campos que no mapeamos dentro de la clase
public record DatosLibro(
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") List<DatosAutores> autores,
    @JsonAlias("languages") List<String> lenguajes,
    @JsonAlias("download_count") Integer numeroDeDescargas){

    }
