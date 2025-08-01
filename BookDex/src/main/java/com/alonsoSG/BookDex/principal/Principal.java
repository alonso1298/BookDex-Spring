package com.alonsoSG.BookDex.principal;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.alonsoSG.BookDex.model.Autor;
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
    private List<String> idiomas;


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
                    listarPorIdioma();
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
        libros.stream().sorted(Comparator.comparing(Libro::getTitulo))
            .forEach(l -> System.out.println(
                "Titulo: " + l.getTitulo() + 
                    "\nAutor: " + l.getAutor().stream()
                    .map(Autor::getNombre) // Solo se obtiene los nombres
                    .collect(Collectors.joining(", ")) +
                    "\nNumero de descargas: " + l.getNumeroDeDescargas() +
                    "\nIdioma: " + l.getIdioma()
            ));
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = repositorioAutor.findAll();
        autores.stream().sorted(Comparator.comparing(Autor::getNombre))
            .forEach(a -> System.out.println(
                "\nNombre: " + a.getNombre() +
                "\nFecha de Nacimiento: " + a.getFechaDeNacimiento() +
                "\nFecha de Muerte: " + a.getFechaDeFallecimiento()
            ));
    }

    private void autoresVivosEnDeterminadoAnio() {
        System.out.println("Indica el intervalo de fechas para la busqueda");
        System.out.println("Fecha de nacimiento máxima");
        Integer fechaMaxima = teclado.nextInt();
        System.out.println("Fecha de Nacimiento mínima");
        Integer fechaMinima = teclado.nextInt();
        teclado.nextLine(); // Limpia el Buffer
        List<Autor> autores = repositorioAutor.busquedaJPQL(fechaMaxima, fechaMinima);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores");
            return;
        }
        autores.stream().distinct()
            .forEach(a -> {
                System.out.println(
                    "Autor: " + a.getNombre() +
                    "\nFecha de nacimiento: " + a.getFechaDeFallecimiento() +
                    "\nFecha de Muerte: " + a.getFechaDeFallecimiento() +
                    "\nLibros: ");
                a.getLibro().forEach(l -> System.out.println("- " + l.getTitulo()));
            });
        int numeroLibros = repositorioAutor.cuentaLibrosPorFechas(fechaMaxima,fechaMinima);
        System.out.println("Número total de libros escritos " +
                "por autores nacidos en el rango indicado" + numeroLibros);
    }
    private void listarPorIdioma(){
        List<Libro> libros = repositorio.busquedaSQLNativo();
        idiomas = libros.stream()
            .sorted(Comparator.comparing(Libro::getIdioma))
            .map(Libro::getIdioma)
            .distinct()
            .collect(Collectors.toList());
        for(int i = 0; i < idiomas.size(); i++){
            System.out.println(i+1 +" Opción idioma: " + idiomas.get(i));
        }
    }
    
}
