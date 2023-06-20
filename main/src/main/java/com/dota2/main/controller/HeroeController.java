// Author: Felipe Reyes { Nekosor }
package com.dota2.main.controller;

import com.dota2.main.model.Heroe;
import com.dota2.main.service.AlmacenamientoService;
import com.dota2.main.service.HeroeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HeroeController {

    @Autowired
    private HeroeService heroeService;
    @Autowired
    private AlmacenamientoService almacenamientoService;


 @PostMapping("/heroes/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Heroe heroe = heroeService.getHeroeById(id);
        String imageUrl = almacenamientoService.store(file);
        heroe.setImageUrl(imageUrl);
        heroeService.saveHeroe(heroe);
        return ResponseEntity.ok().body("Imagen cargada con éxito: " + imageUrl);
    }


    @GetMapping("/heroes")
    public String showHeroes(Model model) {
        model.addAttribute("heroes", heroeService.getAllHeroes());
        return "heroes";
    }

   @PostMapping("/saveHeroe")
public String saveHeroe(@ModelAttribute Heroe heroe, @RequestParam("image") MultipartFile file, Model model) {
    if (!file.isEmpty()) {
        String imageUrl = almacenamientoService.store(file);
        heroe.setImageUrl(imageUrl);
    }
  heroeService.saveHeroe(heroe);

    return "redirect:/heroes";
}

    @GetMapping("/deleteHeroe/{id}")
    public String deleteHeroe(@PathVariable(value = "id") long id) {
        heroeService.deleteHeroeById(id);
        return "redirect:/heroes";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdateHeroeUpdate(@PathVariable(value = "id") long id, Model model) {
        Heroe heroe = heroeService.getHeroeById(id);
        model.addAttribute("heroe", heroe);
        return "update_heroe";
    }

    @GetMapping("/showNewHeroeForm")
    public String showNewHeroeForm(Model model) {
        Heroe heroe = new Heroe();
        model.addAttribute("heroe", heroe);
        return "new_heroe";
    }
}