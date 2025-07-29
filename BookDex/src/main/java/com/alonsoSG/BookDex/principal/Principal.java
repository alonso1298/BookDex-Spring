package com.alonsoSG.BookDex.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.alonsoSG.BookDex.model.Datos;
import com.alonsoSG.BookDex.model.DatosLibro;
import com.alonsoSG.BookDex.service.ConsumoAPI;
import com.alonsoSG.BookDex.service.ConvierteDatos;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibro> librosBuscados = new ArrayList<>();


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

            switch (opcion) {
                case 1:
                    buscarSeriePorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    
                    break;
                case 4:
                    
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
        System.out.println("Escribe el titulo del libro que deseas buscar");
        String tituloLibro = teclado.nextLine();
        String json = consumoAPI.obtenerDatos(URL_BASE + "?search="  + tituloLibro.replace(" ", "+"));
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datos.resultados().stream()
            .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
            .findFirst();

        if(libroBuscado.isPresent()){
            DatosLibro libro = libroBuscado.get();
            librosBuscados.add(libro);
            System.out.println("El libro buscado es: " + libroBuscado.get());
        }else {
            System.out.println("Libro no encontrado");
        }
    }

    private void mostrarLibrosRegistrados(){
        if(librosBuscados.isEmpty()){
            System.out.println("No hay libros registrados todavía. ");
        }else{
            System.out.println("Libros registados: \n");
            for (int i = 0; i < librosBuscados.size(); i++) {
                DatosLibro libro = librosBuscados.get(i);
                System.out.println((i + 1) + ". " + libro.titulo());
            }
        }
    }
}
