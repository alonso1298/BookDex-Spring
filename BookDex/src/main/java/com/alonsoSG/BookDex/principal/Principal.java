package com.alonsoSG.BookDex.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.alonsoSG.BookDex.model.DatosLibro;
import com.alonsoSG.BookDex.model.DatosLibros;
import com.alonsoSG.BookDex.model.Libro;
import com.alonsoSG.BookDex.repository.AutorRepository;
import com.alonsoSG.BookDex.repository.LibroRepository;
import com.alonsoSG.BookDex.service.ConsumoAPI;
import com.alonsoSG.BookDex.service.ConvierteDatos;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=%20";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private AutorRepository repositorioAutor;
    String nombreLibro;
    private List<DatosLibro> librosBuscados = new ArrayList<>();


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
        System.out.println("Ingresa el titulo del libro a buscar: ");
        var nombreLibro = teclado.nextLine();
        
        String url = URL_BASE + nombreLibro.replace(" ", "+");
        System.out.println("URL que se consultará: " + url);

        var json = consumoAPI.obtenerDatos(url);

        var librosTotal = conversor.obtenerDatos(json, DatosLibros.class);

        // Busca libros en la API
        Libro libro = librosTotal.libros().stream()
                .filter(l -> l.titulo() !=null && l.titulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                .findFirst()
                .map(Libro::new)
                .orElse(null);

        if (libro == null) {
            System.out.println("No se encontró el libro.");
            return;
        }

        // Verificar existencia en la BD
        Optional<Libro> libroExistente = repositorio.findByTituloContainsIgnoreCase(libro.getTitulo());
        if(libroExistente.isPresent()){
            System.out.println("El libro ya existe en la base de datos: " + libroExistente.get().getTitulo());
            return;
        }
        // Guarda libro en la BD 
        repositorio.save(libro);
        System.out.println("El libro: " + nombreLibro + "fue guardado exitosamente en la base de datos.");
    }
    
    private void mostrarLibrosRegistrados() {
        List<Libro> libros = repositorio.findAll();
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
