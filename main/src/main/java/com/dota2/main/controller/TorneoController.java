// Author: Felipe Reyes { Nekosor }
package com.dota2.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dota2.main.model.Torneo;
import com.dota2.main.service.TorneoService;

@Controller
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

   
    @GetMapping("/torneos")
    public String viewHomePage(Model model) {
        model.addAttribute("listTorneos", torneoService.getAllTorneos());
        return "torneos";
    }

    @GetMapping("/showNewTorneoForm")
    public String showNewTorneoForm(Model model) {
     
        Torneo torneo = new Torneo();
        model.addAttribute("torneo", torneo);
        return "new_torneo";
    }

    @PostMapping("/saveTorneo")
    public String saveTorneo(@ModelAttribute("torneo") Torneo torneo) {
 
        torneoService.saveTorneo(torneo);
        return "redirect:/torneos";
    }

    @GetMapping("/showFormForUpdateTorneo/{id}")
    public String showFormForUpdateTorneo(@PathVariable(value = "id") long id, Model model) {
   
        Torneo torneo = torneoService.getTorneoById(id);


        model.addAttribute("torneo", torneo);
        return "update_torneo";
    }

    @GetMapping("/deleteTorneo/{id}")
    public String deleteTorneo(@PathVariable(value = "id") long id) {

        this.torneoService.deleteTorneoById(id);
        return "redirect:/torneos";
    }
}