package mag.ej05.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import mag.ej05.FormInfo;
import mag.ej05.services.MoviesService;

@Controller
public class MoviesController {

    @Autowired
    MoviesService moviesService;

    // Declaramos las variables de los votos de cada foto
    int votosFoto1 = 0;
    int votosFoto2 = 0;
    int votosFoto3 = 0;

    // Variable para almacenar error email duplicado
    String errorTxt = null;

    // Variable para almacenar mensaje voto realizado
    String votoOk = null;

    @GetMapping("/")
    public String showMovies(Model model) {

        model.addAttribute("formInfo", new FormInfo());

        model.addAttribute("votosFoto1", votosFoto1);
        model.addAttribute("votosFoto2", votosFoto2);
        model.addAttribute("votosFoto3", votosFoto3);

        model.addAttribute("error", errorTxt);
        model.addAttribute("votoOk", votoOk);

         // Reseteamos las variables de mensajes una vez mostradas
        errorTxt = null;
        votoOk = null; 
        return "indexView";
    }

    @PostMapping("/")
    public String showMoviesVotes(@Valid @ModelAttribute FormInfo formInfo,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // Los errores de validación se procesarán automáticamente con `th:errors` en la
            // vista
            return "indexView";
        }
        try {
            moviesService.emailAdd(formInfo.getEmail());
            switch (formInfo.getVoto()) {
                case "peli1":
                    votosFoto1++;
                    break;
                case "peli2":
                    votosFoto2++;
                    break;
                case "peli3":
                    votosFoto3++;
                    break;
                default:
                    break;
            }
            votoOk ="Gracias por tu voto!";
        } catch (RuntimeException ex) {
            errorTxt = ex.getMessage();
        }

        return "redirect:/";
    }
}