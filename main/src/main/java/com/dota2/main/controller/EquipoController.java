// Author: Felipe Reyes { Nekosor }
package com.dota2.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dota2.main.model.Equipo;
import com.dota2.main.service.EquipoService;

@Controller
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

  
    @GetMapping("/equipos")
    public String viewHomePage(Model model) {
        model.addAttribute("listEquipos", equipoService.getAllEquipos());
        return "equipos";
    }

    @GetMapping("/showNewEquipoForm")
    public String showNewEquipoForm(Model model) {
  
        Equipo equipo = new Equipo();
        model.addAttribute("equipo", equipo);
        return "new_equipo";
    }

    @PostMapping("/saveEquipo")
    public String saveEquipo(@ModelAttribute("equipo") Equipo equipo) {
    
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