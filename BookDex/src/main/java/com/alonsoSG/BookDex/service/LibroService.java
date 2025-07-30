package com.alonsoSG.BookDex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alonsoSG.BookDex.repository.LibroRepository;

@Service
public class LibroService {
    @Autowired
    private LibroRepository repository;

}
