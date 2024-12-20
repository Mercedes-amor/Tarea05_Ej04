package mag.ej05.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import mag.ej05.FormInfo;
import mag.ej05.Pais;
import mag.ej05.services.PaisesService;

@Controller
public class PaisesController {

    @Autowired
    PaisesService paisesService;


    @GetMapping("/")
    public String showForm(@ModelAttribute FormInfo formInfo, Model model) {

        model.addAttribute("formInfo", new FormInfo());

        // Cargamos la lista de nombres para que se muestren en el select
        List<String> listaNombres = paisesService.getPaises();
        model.addAttribute("listaNombres", listaNombres);


        return "indexView";
    }

    @PostMapping("/")
    public String showFormInfo(@Valid @ModelAttribute FormInfo formInfo,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // Si hay errores, recarga la lista de países
            model.addAttribute("listaNombres", paisesService.getPaises());
            return "indexView";
        }

        try {

            // Buscamos el pais a través del método del servicio getPais()
            Pais paisEncontrado = paisesService.getPais(formInfo.getNombrePais());

            if (paisEncontrado != null) {

                //Recarga lista paises
                model.addAttribute("listaNombres", paisesService.getPaises());
                //Pasa a la vista los datos de la capital y población
                model.addAttribute("capital", paisEncontrado.getCapital());
                model.addAttribute("poblacion", paisEncontrado.getPoblacion());
            } else {
                //Recarga lista paises
                model.addAttribute("listaNombres", paisesService.getPaises());
                model.addAttribute("error", "País no encontrado");
            }

            return "indexView";

        } catch (RuntimeException ex) {
            model.addAttribute("error", "Error al procesar el formulario");

        }

        // Recargamos la misma vista con los datos actualizados
        model.addAttribute("listaNombres", paisesService.getPaises());
        return "indexView";
    }
}