package com.miage.altea.game_ui.trainers.services;

import com.miage.altea.game_ui.trainers.bo.Trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class TrainerServiceImpl implements TrainerService {
    private RestTemplate restTemplate;

    private String trainerApiUrl;

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setTrainerApiUrl(String trainerApiUrl) {
        this.trainerApiUrl = trainerApiUrl;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return Arrays.asList(Objects.requireNonNull
                (restTemplate.getForObject(trainerApiUrl + "/trainers", Trainer[].class)));
    }

    @Override
    @Cacheable("trainer")
    public Trainer getTrainer(String name) {
        return Objects.requireNonNull
                (restTemplate.getForObject(trainerApiUrl + "/trainers/"+name, Trainer.class));
    }
/*
    @Override
    public Trainer createTrainer(Trainer trainer) {
        restTemplate.put(trainerApiUrl + "/trainers", trainer);
        return Objects.requireNonNull
                (restTemplate.getForObject(trainerApiUrl + "/trainers/"+trainer.getName(), Trainer.class));
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        return null;
    }

    @Override
    public void deleteTrainer(String name) {

    }*/
}