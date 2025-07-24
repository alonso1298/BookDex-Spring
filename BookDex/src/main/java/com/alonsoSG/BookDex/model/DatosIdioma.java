package com.alonsoSG.BookDex.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosIdioma(
    @JsonAlias("languages") String idiomas) {
}
