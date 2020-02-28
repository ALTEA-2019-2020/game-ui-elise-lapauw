package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.services.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {

    PokemonTypeService pokemonTypeService;


    @GetMapping(value = "/pokedex")
    public ModelAndView pokedex(){
        var modelAndView = new ModelAndView("pokedex");
        modelAndView.addObject("pokemonTypes",pokemonTypeService.listPokemonsTypes());
        return modelAndView;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

}
