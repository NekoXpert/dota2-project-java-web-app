// Author: Felipe Reyes { Nekosor }
package com.dota2.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dota2.main.model.Equipo;
import com.dota2.main.model.Jugador;
import com.dota2.main.service.AlmacenamientoService;
import com.dota2.main.service.EquipoService;

@Controller
public class EquipoController {

    @Autowired
    private EquipoService equipoService;
@Autowired
    private AlmacenamientoService almacenamientoService;
  
    @GetMapping("/equipos")
    public String viewHomePage(Model model) {
        model.addAttribute("listEquipos", equipoService.getAllEquipos());
        return "equipos";
    }

  @PostMapping("/equipos/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Equipo equipo = equipoService.getEquipoById(id);
        String imageUrl = almacenamientoService.store(file);
        equipo.setImageUrl(imageUrl);
        equipoService.saveEquipo(equipo);
        return ResponseEntity.ok().body("Imagen cargada con éxito: " + imageUrl);
    }


    @GetMapping("/showNewEquipoForm")
    public String showNewEquipoForm(Model model) {
  
        Equipo equipo = new Equipo();
        model.addAttribute("equipo", equipo);
        return "new_equipo";
    }

   /*@PostMapping("/saveEquipo")
public String saveEquipo(@ModelAttribute Equipo equipo, @RequestParam("image") MultipartFile file, Model model) {
    if (!file.isEmpty()) {
        String imageUrl = almacenamientoService.store(file);
        equipo.setImageUrl(imageUrl);
    }

   

    return "redirect:/equipos";
}
*/


@PostMapping("/saveEquipo")
public String saveEquipo(@ModelAttribute Equipo equipo, @RequestParam("image") MultipartFile file, Model model) {
    if (!file.isEmpty()) {
        String imageUrl = almacenamientoService.store(file);
        equipo.setImageUrl(imageUrl);
    }

    // Obtener los jugadores del formulario (si los hay)
    List<Jugador> jugadores = equipo.getJugadores();
    if (jugadores != null) {
        // Establecer el equipo en cada jugador
        for (Jugador jugador : jugadores) {
            jugador.setEquipo(equipo);
        }
        // Establecer la lista de jugadores en el equipo
        equipo.setJugadores(jugadores);
    }

    equipoService.saveEquipo(equipo);

    return "redirect:/equipos";
}





    @GetMapping("/showFormForUpdateEquipo/{id}")
    public String showFormForUpdateEquipo(@PathVariable(value = "id") long id, Model model) {
    
        Equipo equipo = equipoService.getEquipoById(id);

    
        model.addAttribute("equipo", equipo);
        return "update_equipo";
    }

    @GetMapping("/deleteEquipo/{id}")
    public String deleteEquipo(@PathVariable(value = "id") long id) {
   
        this.equipoService.deleteEquipoById(id);
        return "redirect:/equipos";
    }
}