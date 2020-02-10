package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.services.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {

    @Autowired
    PokemonTypeService pokemonTypeService;


    @GetMapping("/pokedex")
    public ModelAndView pokedex(){
        var modelAndView = new ModelAndView("pokedex");
        modelAndView.addObject("pokemonTypes",pokemonTypeService.listPokemonsTypes());
        return modelAndView;
    }

    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }
}
