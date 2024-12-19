package mag.ej05.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MoviesService {

    // Instanciamos un ArrayList para almacenar los emails
    private List<String> listaEmails = new ArrayList<>();

    // Función para añadir un email, 
    //verificando primero si ya está registrado

    public void emailAdd(String email) {
        // Verificamos si el correo ya está en la lista
        if (listaEmails.contains(email)) {
            throw new RuntimeException("Este email ya ha votado");
        }
        // Agregamos el correo a la lista
        listaEmails.add(email);
    }

}
