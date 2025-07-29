package com.alonsoSG.BookDex.principal;

import java.util.Scanner;

import com.alonsoSG.BookDex.model.DatosLibro;
import com.alonsoSG.BookDex.service.ConsumoAPI;
import com.alonsoSG.BookDex.service.ConvierteDatos;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books";
    private ConvierteDatos conversor = new ConvierteDatos();


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
                    getDatosLibro();
                    break;
                case 2:
                    
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

    private DatosLibro getDatosLibro() {
            System.out.println("Escribe el titulo del libro que deseas buscar");
            String nombreLibro = teclado.nextLine();
            String json = consumoAPI.obtenerDatos(URL_BASE + "?search="  + nombreLibro.replace(" ", "%20"));
            System.out.println(json);
            DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
            return datos;
        }
}
