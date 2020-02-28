package com.miage.altea.game_ui.pokemonTypes.services;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface PokemonTypeService {
    List<PokemonType> listPokemonsTypes();
    PokemonType getPokemon(int id);
    void setRestTemplate(RestTemplate restTemplate);
    void setPokemonTypeServiceUrl(String pokemonServiceUrl);
}
