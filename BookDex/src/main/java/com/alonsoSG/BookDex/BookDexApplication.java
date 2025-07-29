package com.alonsoSG.BookDex;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
