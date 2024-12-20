package mag.ej05;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mag.ej05.services.PaisesService;

@SpringBootApplication
public class Ej05Application {

	public static void main(String[] args) {
		SpringApplication.run(Ej05Application.class, args);
		System.out.println("Hola mundo ejercicio 5.4");

	}

	// Bean CommandLineRunner para cargar los datos iniciales
	@Bean
	CommandLineRunner initData(PaisesService paisesService) {
		return args -> {
			// Llamamos al método del service para cargar los países
			paisesService.cargarPaisesDesdeFichero();

			System.out.println("Datos de países cargados: " + paisesService.getPaises());
		};
	}

}
