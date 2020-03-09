package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.services.TrainerService;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    @Autowired
    private final TrainerService trainerService;

    TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    ModelAndView getAllTrainers(){
        var modelAndView = new ModelAndView("trainers");
        modelAndView.addObject("trainer",trainerService.getAllTrainers());
        return modelAndView;
    }

    @GetMapping("/{name}")
    ModelAndView getTrainer(@PathVariable String name){
        var modelAndView = new ModelAndView("trainer");
        modelAndView.addObject("trainer",trainerService.getTrainer(name));
        return modelAndView;
    }
   /* @PutMapping()
    Trainer updateTrainer(@RequestBody Trainer trainer) {
        return this.trainerService.createTrainer(trainer);
    }
    @PostMapping()
    Trainer newTrainer(@RequestBody Trainer trainer) {
        return this.trainerService.createTrainer(trainer);
    }
    @DeleteMapping(value="/{name}")
    void deleteTrainer(@PathVariable String name) {
        this.trainerService.deleteTrainer(name);
    }*/

}