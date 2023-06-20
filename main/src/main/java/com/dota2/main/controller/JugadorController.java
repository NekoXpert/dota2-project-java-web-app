// Author: Felipe Reyes { Nekosor }
package com.dota2.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.dota2.main.model.Jugador;
import com.dota2.main.service.JugadorService;
import com.dota2.main.service.AlmacenamientoService;
import com.dota2.main.service.EquipoService;
import com.dota2.main.service.HeroeService;
import com.dota2.main.service.TorneoService;



@Controller
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;
    @Autowired
    private EquipoService equipoService;
    @Autowired
    private TorneoService torneoService;
    @Autowired
    private HeroeService heroeService;
    @Autowired
    private AlmacenamientoService almacenamientoService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listJugadores", jugadorService.getAllJugadores());
        return "index";
    }

    @PostMapping("/jugadores/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Jugador jugador = jugadorService.getJugadorById(id);
        String imageUrl = almacenamientoService.store(file);
        jugador.setImageUrl(imageUrl);
        jugadorService.saveJugador(jugador);
        return ResponseEntity.ok().body("Imagen cargada con éxito: " + imageUrl);
    }

    @GetMapping("/showNewJugadorForm")
    public String showNewJugadorForm(Model model) {

        Jugador jugador = new Jugador();
        model.addAttribute("jugador", jugador);
        model.addAttribute("listHeroes", heroeService.getAllHeroes());
        model.addAttribute("listEquipos", equipoService.getAllEquipos());
        model.addAttribute("listTorneos", torneoService.getAllTorneos());
        return "new_jugador";
    }

    @PostMapping("/saveJugador")
    public String saveJugador( @ModelAttribute Jugador jugador, @RequestParam("image") MultipartFile file, BindingResult result,
            Model model) {
        if (!file.isEmpty()) {
            String imageUrl = almacenamientoService.store(file);
            jugador.setImageUrl(imageUrl);
        }
        if (result.hasErrors()) {
            model.addAttribute("jugador", jugador);
            return "new_jugador";
        } else {
            jugadorService.saveJugador(jugador);
            return "redirect:/";
        }
    }

    @GetMapping("/showFormForUpdateJugador/{id}")
    public String showFormForUpdateJugador(@PathVariable(value = "id") long id, Model model) {
        Jugador jugador = jugadorService.getJugadorById(id);
        model.addAttribute("jugador", jugador);
        model.addAttribute("listHeroes", heroeService.getAllHeroes());
        model.addAttribute("listEquipos", equipoService.getAllEquipos());
        model.addAttribute("listTorneos", torneoService.getAllTorneos());
        return "update_jugador";
    }

    @PostMapping("/showFormForUpdateJugador")
    public String updateJugador(@ModelAttribute("jugador") Jugador jugador) {
        jugadorService.saveJugador(jugador);
        return "redirect:/";
    }

    @GetMapping("/deleteJugador/{id}")
    public String deleteJugador(@PathVariable(value = "id") long id) {

        this.jugadorService.deleteJugadorById(id);
        return "redirect:/";

    }

}