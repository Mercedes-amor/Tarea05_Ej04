package mag.ej05;

import jakarta.validation.constraints.NotNull;

public class FormInfo {
// @NotEmpty(message = "Debe seleccionar una película para votar")


@NotNull(message = "Debe seleccionar un pais")
private String nombrePais;


//Getters y Setters

public String getNombrePais() {
    return nombrePais;
}

public void setNombrePais(String nombrePais) {
    this.nombrePais = nombrePais;
}

}
