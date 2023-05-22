// Author: Felipe Reyes { Nekosor }
package com.dota2.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dota2.main.model.Jugador;
import com.dota2.main.service.JugadorService;
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

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listJugadores", jugadorService.getAllJugadores());
        return "index";
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
    public String saveJugador(@ModelAttribute("jugador") Jugador jugador) {

   
        jugadorService.saveJugador(jugador);
        return "redirect:/";
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

    @PostMapping("/updateJugador")
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