package com.miage.altea.game_ui.trainers.services;

import com.miage.altea.game_ui.trainers.bo.Trainer;

import java.util.List;

public interface TrainerService {

    List<Trainer> getAllTrainers();
    Trainer getTrainer(String name);
    // Trainer createTrainer(Trainer trainer);
    // void deleteTrainer(String name);
}