package com.miage.altea.game_ui.pokemonTypes.services;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    String pokemonServiceUrl;
    RestTemplate restTemplate;

    public List<PokemonType> listPokemonsTypes() {
        PokemonType[] l = restTemplate.getForObject(pokemonServiceUrl + "pokemon-types/", PokemonType[].class);
        for (Object p : l) {
            System.out.println(p.toString());
        }
        System.out.println(pokemonServiceUrl+"pokemon-types/");
        return Arrays.asList(restTemplate.getForObject(pokemonServiceUrl + "pokemon-types/", PokemonType[].class));
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}