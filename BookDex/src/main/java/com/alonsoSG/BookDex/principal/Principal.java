package com.alonsoSG.BookDex.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alonsoSG.BookDex.model.Datos;
import com.alonsoSG.BookDex.model.DatosLibros;
import com.alonsoSG.BookDex.model.Libro;
import com.alonsoSG.BookDex.repository.AutorRepository;
import com.alonsoSG.BookDex.repository.LibroRepository;
import com.alonsoSG.BookDex.service.ConsumoAPI;
import com.alonsoSG.BookDex.service.ConvierteDatos;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private AutorRepository repositorioAutor;
    private List<DatosLibros> librosBuscados = new ArrayList<>();


    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.repositorioAutor = autorRepository;
    }

    public  void muestraMenu(){
        Integer opcion = -1;
        while (opcion != 0) {
            String menu = """
                    Elija la opción atravez de su número:
                    1 - Buscar un libro por su titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir.
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarSeriePorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    autoresVivosEnDeterminadoAnio();
                    break;
                case 5:
                    
                    break;
                case 0:
                    System.out.println("Cerrando Programa...");
                    break;
            
                default:
                System.out.println("Opcion Inválida");
                    break;
            }
        }
    }

    
    
    
    private void buscarSeriePorTitulo() {
        System.out.println("Ingresa el titulo del libro a buscar");
    }
    
    private void mostrarLibrosRegistrados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarLibrosRegistrados'");
    }
    private void autoresVivosEnDeterminadoAnio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'autoresVivosEnDeterminadoAnio'");
    }
    
    private void listarAutoresRegistrados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarAutoresRegistrados'");
    }
}
