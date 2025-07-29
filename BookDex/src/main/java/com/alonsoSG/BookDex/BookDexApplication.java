package com.alonsoSG.BookDex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alonsoSG.BookDex.principal.Principal;

@SpringBootApplication
public class BookDexApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BookDexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraMenu();
	}

}
