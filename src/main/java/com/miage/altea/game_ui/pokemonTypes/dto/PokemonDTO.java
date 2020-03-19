package com.miage.altea.game_ui.pokemonTypes.dto;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;

public class PokemonDTO {
    private PokemonType pokemonType;
    private int level;

    public PokemonDTO(PokemonType pokemonType, int level) {
        this.pokemonType = pokemonType;
        this.level = level;
    }

    public PokemonType getPokemonType() {
        return pokemonType;
    }

    public void setPokemonType(PokemonType pokemonType) {
        this.pokemonType = pokemonType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
