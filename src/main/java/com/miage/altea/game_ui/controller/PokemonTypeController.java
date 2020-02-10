package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.services.PokemonTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {

    PokemonTypeService pokemonTypeService;

    public ModelAndView pokedex(){
        var modelAndView = new ModelAndView("pokedex");
        modelAndView.addObject("pokemonTypes");
        return modelAndView;
    }

    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }
}
