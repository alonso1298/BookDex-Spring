package com.alonsoSG.BookDex.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alonsoSG.BookDex.model.Datos;
import com.alonsoSG.BookDex.model.DatosLibros;
import com.alonsoSG.BookDex.model.Libro;
import com.alonsoSG.BookDex.repository.LibroRepository;
import com.alonsoSG.BookDex.service.ConsumoAPI;
import com.alonsoSG.BookDex.service.ConvierteDatos;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private List<DatosLibros> librosBuscados = new ArrayList<>();


    public Principal(LibroRepository repository) {
        this.repositorio = repository;
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

    private DatosLibros getDatosLibros(){
        System.out.println("Escribe el titulo del libro que deseas buscar");
        String nombreLibro = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + "?search="  + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        return datos;
    }

    private void buscarSeriePorTitulo() {
            DatosLibros datos = getDatosLibros();
            Libro libro = new Libro(datos);
            repositorio.save(libro);
    }
    private void mostrarLibrosRegistrados(){
        if(librosBuscados.isEmpty()){
            System.out.println("No hay libros registrados todavía. ");
        }else{
            System.out.println("Libros registados: \n");
            for (int i = 0; i < librosBuscados.size(); i++) {
                DatosLibros libro = librosBuscados.get(i);
                System.out.println((i + 1) + ". " + libro.titulo());
            }
        }
    }

    private void listarAutoresRegistrados() {
        String json = consumoAPI.obtenerDatos(URL_BASE);
        Datos datos = conversor.obtenerDatos(json, Datos.class);

        System.out.println("\n Autores registrados: ");
        datos.resultados().stream()
            .map(DatosLibros::autorPrincipal)
            .filter(autor -> autor != null && autor.nombre() != null)
            .distinct()
            .forEach(autor -> System.out.println("-" + autor.nombre()));
    }

    private void autoresVivosEnDeterminadoAnio(){
        System.out.println("Ingresa el intervalo de años que deseas consultar");
        System.out.println("Ingresa el primer año: ");
        Integer primeranio = teclado.nextInt();
        System.out.println("Ingresa el segundo año ");
        Integer segundoAnio = teclado.nextInt();
        teclado.nextLine();

        if(segundoAnio < primeranio) {
            System.out.println("El primer año tiene que ser menor o igual al segundo.");
            return;
        }

        String url = URL_BASE + "?author_year_start=" + primeranio + "&author_year_end=" + segundoAnio;
        String json = consumoAPI.obtenerDatos(url);
        Datos datos = conversor.obtenerDatos(json, Datos.class);

        if(datos.resultados().isEmpty()){
            System.out.println("No se encontraron autores vivos entre esos años ");
            return;
        }
        
        System.out.println("\n Se encontraron los siguientes autore: ");
        datos.resultados().stream()
            .map(DatosLibros::autorPrincipal)
            .distinct()
            .forEach(autor -> System.out.println("-" + autor.nombre()
                + "(" + autor.fechaDeNacimiento() + "-" +
                autor.fechaDeFallecimiento() != null ? autor.fechaDeFallecimiento() : "Presente" + ")"));
    }
}
