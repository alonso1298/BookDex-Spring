package com.alonsoSG.BookDex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alonsoSG.BookDex.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);
    Optional<Libro> findByTitulo(String titulo);
    @Query(value = "select * from libro",nativeQuery = true)
    List<Libro> busquedaSQLNativo();
}
