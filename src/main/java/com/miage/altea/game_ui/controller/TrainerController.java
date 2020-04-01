package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.dto.PokemonDTO;
import com.miage.altea.game_ui.pokemonTypes.services.PokemonTypeService;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    @Autowired
    private final TrainerService trainerService;
    @Autowired
    private final PokemonTypeService pokemonTypeService;

    TrainerController(TrainerService trainerService, PokemonTypeService pokemonTypeService){

        this.trainerService = trainerService;
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping("/")
    ModelAndView getAllTrainers(){
        var modelAndView = new ModelAndView("trainers");
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Trainer> trainers = trainerService.getAllTrainers()
                .stream()
                .filter((Trainer t) -> !t.getName().equals(currentUser))
                .collect(Collectors.toList());
        modelAndView.addObject("trainers", trainers);
        return modelAndView;
    }

    @GetMapping("/{name}")
    ModelAndView getTrainer(@PathVariable String name){
        var modelAndView = new ModelAndView("trainer");
        return getModelAndView(modelAndView, name);
    }


    @GetMapping("/profile")
    ModelAndView getProfile(){
        var modelAndView = new ModelAndView("profile");
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return getModelAndView(modelAndView, currentUser);
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView, String currentUser) {
        Trainer trainer = trainerService.getTrainer(currentUser);
        var pokemonTeam = trainer.getTeam()
                .stream()
                .map((Pokemon p) ->
                    new PokemonDTO(pokemonTypeService.getPokemon(p.getPokemonTypeId()), p.getLevel()))
                .collect(Collectors.toList());
        modelAndView.addObject("trainer", trainer);
        modelAndView.addObject("team", pokemonTeam);
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