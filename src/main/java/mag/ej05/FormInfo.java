package mag.ej05;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FormInfo {
// @NotEmpty(message = "Debe seleccionar una película para votar")
@NotNull(message = "Debe seleccionar una película para votar")
private String voto;

@NotEmpty(message = "Debe introducir su email para votar")
@Email(message = "El correo electrónico debe tener un formato válido")
@NotNull(message = "Debe introducir su email para votar")
private String email;

//Getters y Setters
public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getVoto() {
    return voto;
}

public void setVoto(String voto) {
    this.voto = voto;
}




}
