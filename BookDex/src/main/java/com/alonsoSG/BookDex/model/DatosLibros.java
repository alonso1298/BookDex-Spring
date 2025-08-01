package com.alonsoSG.BookDex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros (

        @JsonAlias("results")List<DatosLibro> libros
) {
}
