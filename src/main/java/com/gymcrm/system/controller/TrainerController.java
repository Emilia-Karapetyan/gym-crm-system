package com.gymcrm.system.controller;

import com.gymcrm.system.service.TrainerService;
import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.validate.TrainerRequestValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TrainerController {
    private final TrainerService trainerService;
    private final TrainerRequestValidator trainerRequestValidator;

    public TrainerController(TrainerService trainerService, TrainerRequestValidator trainerRequestValidator) {
        this.trainerService = trainerService;
        this.trainerRequestValidator = trainerRequestValidator;
    }

    public TrainerDto create(TrainerDto trainerDto) {
        trainerRequestValidator.validateCreateRequest(trainerDto);

        return trainerService.create(trainerDto);
    }

    public TrainerDto update(Long id, TrainerDto trainerDto) {
        return trainerService.update(id, trainerDto);
    }

    public Optional<TrainerDto> get(Long id) {
        return trainerService.getById(id);
    }

    public List<TrainerDto> getAll() {
        return trainerService.getAll();
    }
}
