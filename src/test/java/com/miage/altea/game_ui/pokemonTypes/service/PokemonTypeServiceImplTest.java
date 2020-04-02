package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.services.PokemonTypeServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PokemonTypeServiceImplTest {
    @Autowired
    PokemonTypeServiceImpl pokemonTypeService;

    @Mock
    RestTemplate restTemplate;

    @Value("${pokemonType.service.url}/pokemon-types/{id}")
    String expectedUrl;

    @Autowired
    CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        pokemonTypeService.setRestTemplate(restTemplate);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("Pikachu");
        when(restTemplate.getForObject(expectedUrl, PokemonType.class, 25)).thenReturn(pikachu);
    }

    @Test
    void getPokemonType_shouldUseCache() {
        pokemonTypeService.getPokemon(25);

        // rest template should have been called once
        verify(restTemplate).getForObject(expectedUrl, PokemonType.class, 25);

        pokemonTypeService.getPokemon(25);

        // rest template should not be called anymore because result is in cache !
        verifyNoMoreInteractions(restTemplate);

        // one result should be in cache !
        var cachedValue = cacheManager.getCache("pokemon-types").get(25).get();
        assertNotNull(cachedValue);
        assertEquals(PokemonType.class, cachedValue.getClass());
        assertEquals("Pikachu", ((PokemonType)cachedValue).getName());
    }


    @Test
    void listPokemonsTypes_shouldCallTheRemoteService() {
        var url = "http://localhost:8080";

        var restTemplate = mock(RestTemplate.class);
        var pokemonServiceImpl = new PokemonTypeServiceImpl();
        pokemonServiceImpl.setRestTemplate(restTemplate);
        pokemonServiceImpl.setPokemonTypeServiceUrl(url);

        var pikachu = new PokemonType();
        pikachu.setName("pikachu");
        pikachu.setId(25);

        var expectedUrl = "http://localhost:8080/pokemon-types";
        when(restTemplate.getForObject(expectedUrl, PokemonType[].class)).thenReturn(new PokemonType[]{pikachu});
        var pokemons = pokemonServiceImpl.listPokemonsTypes();

        assertNotNull(pokemons);
        assertEquals(1, pokemons.size());

        verify(restTemplate).getForObject(expectedUrl, PokemonType[].class);
    }

    @Test
    void pokemonServiceImpl_shouldBeAnnotatedWithService(){
        assertNotNull(PokemonTypeServiceImpl.class.getAnnotation(Service.class));
    }

    @Test
    void setRestTemplate_shouldBeAnnotatedWithAutowired() throws NoSuchMethodException {
        var setRestTemplateMethod = PokemonTypeServiceImpl.class.getDeclaredMethod("setRestTemplate", RestTemplate.class);
        assertNotNull(setRestTemplateMethod.getAnnotation(Autowired.class));
    }

    @Test
    void setPokemonServiceUrl_shouldBeAnnotatedWithValue() throws NoSuchMethodException {
        var setter = PokemonTypeServiceImpl.class.getDeclaredMethod("setPokemonTypeServiceUrl", String.class);
        var valueAnnotation = setter.getAnnotation(Value.class);
        assertNotNull(valueAnnotation);
        assertEquals("${pokemonType.service.url}", valueAnnotation.value());
    }

}