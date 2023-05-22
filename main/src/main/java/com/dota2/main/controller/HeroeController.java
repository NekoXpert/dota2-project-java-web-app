// Author: Felipe Reyes { Nekosor }
package com.dota2.main.controller;

import com.dota2.main.model.Heroe;
import com.dota2.main.service.HeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;




@Controller
public class HeroeController {

    @Autowired
    private HeroeService heroeService;

    @GetMapping("/heroes")
    public String showHeroes(Model model) {
        model.addAttribute("heroes", heroeService.getAllHeroes());
        return "heroes";
    }

   /*
    * @GetMapping("/showFormForUpdateHeroe/{id}")
    * public String showFormForUpdate(@PathVariable(value = "id") long id, Model
    * model) {
    * Heroe heroe = heroeService.getHeroeById(id);
    * model.addAttribute("heroe", heroe);
    * return "update_heroe";
    * }
    */

    @PostMapping("/heroes")
    public String saveHeroe(@ModelAttribute("heroe") Heroe heroe,
            BindingResult bindingResult,
            @RequestParam("newImageFile") MultipartFile newImageFile) {
        if (bindingResult.hasErrors()) {
            return "new_heroe";
        }
        try {
            heroe.setImageFile(newImageFile);
            heroeService.saveHeroe(heroe, newImageFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "new_heroe";
        }
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