package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.services.PokemonTypeService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonTypeControllerTest {
    @Before
    public void before() {
        Mockito.reset(/*mocked objects to reset*/);
        // mock them here or in individual tests
    }
    @Test
    void controllerShouldBeAnnotated(){
        assertNotNull(PokemonTypeController.class.getAnnotation(Controller.class));
    }

    @Test
    void pokemons_shouldReturnAModelAndView() {
        var pokemonTypeService = mock(PokemonTypeService.class);

        when(pokemonTypeService.listPokemonsTypes()).thenReturn(List.of(new PokemonType(), new PokemonType()));

        var pokemonTypeController = new PokemonTypeController();
        pokemonTypeController.setPokemonTypeService(pokemonTypeService);
        var modelAndView = pokemonTypeController.pokedex();

        assertEquals("pokedex", modelAndView.getViewName());
        var pokemons = (List<PokemonType>)modelAndView.getModel().get("pokemonTypes");
        assertEquals(2, pokemons.size());
        verify(pokemonTypeService).listPokemonsTypes();
    }

    @Test
    void pokemons_shouldBeAnnotated() throws NoSuchMethodException {
        var pokemonsMethod = PokemonTypeController.class.getDeclaredMethod("pokedex");
        var getMapping = pokemonsMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/pokedex"}, getMapping.value());
    }
}