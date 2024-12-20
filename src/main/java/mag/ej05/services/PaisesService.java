package mag.ej05.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mag.ej05.Pais;

@Service
public class PaisesService {

    // Instanciamos el Array con la colección de datos dónde añadiremos objetos de
    // la clase Pais que hemos creado.
    // Cada objeto de clase Pais tiene un String nombre, 
    //String capital y Int población.
    private List<Pais> paises = new ArrayList<>();

    // Método para cargar la información desde el csv
    public void cargarPaisesDesdeFichero() {

        // Lo metemos en un try-catch por si el csv viniera con configuración distinta
        try {
            // Usamos el Files.readAllLines para añadir cada línea como elemento del Array lineas
            // De este modo cada elemento del Array tiene una línea(String) con información
            // de UN PAÍS separada por ";"
            List<String> lineas = Files.readAllLines(Paths.get("src/main/resources/static/paises.csv"), 
            StandardCharsets.ISO_8859_1);


            // Ahora procesamos cada línea del array lineas para añadir un objeto de la
            // clase Pais al Array paises
            for (String linea : lineas) { // bucle forEach
                String[] paisInfo = linea.split(";"); // Nos devuelve un array de 3 elementos 
                                                            //[nombre, capital, población] para cada linea(pais)
                String nombre = paisInfo[0].trim(); // Usamos .trim() para eliminar espacios sobrantes si los hubiera
                String capital = paisInfo[1].trim();
                int poblacion = Integer.parseInt(paisInfo[2].trim());
                
                // Creamos un objeto Pais para cada linea y lo añadimos al array
                paises.add(new Pais(nombre, capital, poblacion)); 
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo países.csv: " + e.getMessage());
        }
    }

    // Método para obtener los nombres de los países, devuelve un Array
    public List<String> getPaises() {
        List<String> nombres = new ArrayList<>();
        for (Pais pais : paises) {
            nombres.add(pais.getNombre());
        }
        return nombres;
    }

    // Método para obtener los datos de un país proporcionando el nombre, devuelve un objeto clase Pais
    public Pais getPais(String nombre) {
        for (Pais pais : paises) {
            if (pais.getNombre().equalsIgnoreCase(nombre)) { //Compara 2 String ignorando diferencias entre Mayus y minus
                return pais;
            }
        }
        return null; // Si no se encuentra el país
    }
}
