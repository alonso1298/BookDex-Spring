package com.alonsoSG.BookDex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alonsoSG.BookDex.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);
}
