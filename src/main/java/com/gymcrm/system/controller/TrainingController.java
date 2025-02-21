package com.gymcrm.system.controller;

import com.gymcrm.system.service.TrainingService;
import com.gymcrm.system.service.dto.TrainingDto;
import com.gymcrm.system.service.validate.TrainingRequestValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingRequestValidator trainingRequestValidator;

    public TrainingController(TrainingService trainingService, TrainingRequestValidator trainingRequestValidator) {
        this.trainingService = trainingService;
        this.trainingRequestValidator = trainingRequestValidator;
    }

    public TrainingDto create(TrainingDto trainingDto) {
        trainingRequestValidator.validateCreateRequest(trainingDto);

        return trainingService.create(trainingDto);
    }

    public List<TrainingDto> getAll() {
        return trainingService.getAll();
    }

    public Optional<TrainingDto> getById(Long id) {
        return trainingService.getById(id);
    }

}
