package com.alonsoSG.BookDex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alonsoSG.BookDex.model.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("select a from Autor a \n" +
            "\twhere a.fechaDeNacimiento >= :fechaMinima\n" +
            "\tand  a.fechaDeNacimiento <= :fechaMaxima\n" +
            "\torder by a.fechaDeNacimiento ")
    List<Autor> busquedaJPQL(Integer fechaMaxima, Integer fechaMinima);

    @Query("SELECT COUNT(l) " +
            "FROM Autor a JOIN a.libro l " +
            "WHERE a.fechaDeNacimiento >= :fechaMinima " +
            "AND a.fechaDeNacimiento <= :fechaMaxima")
    int cuentaLibrosPorFechas(@Param("fechaMaxima") Integer fechaMaxima,
                              @Param("fechaMinima") Integer fechaMinima);


    @Query("select a from Autor a \n" +
            "\twhere a.fechaaDeFallecimiento >= :fechaMinima\n" +
            "\tand  a.fechaaDeFallecimiento <= :fechaMaxima\n" +
            "\torder by a.fechaaDeFallecimiento desc")
    List<Autor> busquedaFechaaDeFallecimiento(Integer fechaMaxima, Integer fechaMinima);
    Optional<Autor> findByNombre(String nombre);

    @Query("select a from Autor a \n" +
            "\twhere a.edad = :edadDigitada\n" +
            "\torder by a.edad desc")
    List<Autor> busquedaPorEdad(Integer edadDigitada);

    @Query("select a from Autor a \n" +
            "\twhere a.edad >= :edadMinima\n" +
            "\tand  a.edad <= :edadMaxima\n" +
            "\torder by a.edad desc")
    List<Autor> busquedaRangoEdad(Integer edadMaxima, Integer edadMinima);

}
