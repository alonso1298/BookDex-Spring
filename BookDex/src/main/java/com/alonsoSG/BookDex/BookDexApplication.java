package com.alonsoSG.BookDex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alonsoSG.BookDex.principal.Principal;
import com.alonsoSG.BookDex.repository.LibroRepository;

@SpringBootApplication
public class BookDexApplication implements CommandLineRunner{

	@Autowired
	private LibroRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(BookDexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.muestraMenu();
	}

}
