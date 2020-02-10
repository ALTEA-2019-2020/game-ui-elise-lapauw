package com.miage.altea.game_ui.pokemonTypes.services;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    String pokemonServiceUrl;
    RestTemplate restTemplate;

    public List<PokemonType> listPokemonsTypes() {
        /*PokemonType[] types = restTemplate.getForObject(pokemonServiceUrl + "/pokemon-types/", PokemonType[].class);
        return types != null ? Arrays.asList(types) : new ArrayList<>();*/
        return Arrays.asList(restTemplate.getForObject(this.pokemonServiceUrl+"/pokemon-types/", PokemonType[].class));
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}