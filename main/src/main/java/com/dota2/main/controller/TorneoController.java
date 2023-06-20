// Author: Felipe Reyes { Nekosor }
package com.dota2.main.controller;

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


import com.dota2.main.model.Torneo;
import com.dota2.main.service.AlmacenamientoService;
import com.dota2.main.service.TorneoService;

@Controller
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

   @Autowired
    private AlmacenamientoService almacenamientoService;
    @GetMapping("/torneos")
    public String viewHomePage(Model model) {
        model.addAttribute("listTorneos", torneoService.getAllTorneos());
        return "torneos";
    }
 @PostMapping("/torneos/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Torneo torneo = torneoService.getTorneoById(id);
        String imageUrl = almacenamientoService.store(file);
        torneo.setImageUrl(imageUrl);
        torneoService.saveTorneo(torneo);
        return ResponseEntity.ok().body("Imagen cargada con éxito: " + imageUrl);
    }
    @GetMapping("/showNewTorneoForm")
    public String showNewTorneoForm(Model model) {
     
        Torneo torneo = new Torneo();
        model.addAttribute("torneo", torneo);
        return "new_torneo";
    }

  @PostMapping("/saveTorneo")
public String saveTorneo(@ModelAttribute Torneo torneo, @RequestParam("image") MultipartFile file, Model model) {
    if (!file.isEmpty()) {
        String imageUrl = almacenamientoService.store(file);
        torneo.setImageUrl(imageUrl);
    }

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