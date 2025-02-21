package com.gymcrm.system.controller;

import com.gymcrm.system.service.TraineeService;
import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.validate.TraineeRequestValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TraineeController {
    private final TraineeService traineeService;
    private final TraineeRequestValidator traineeRequestValidator;

    public TraineeController(TraineeService traineeService, TraineeRequestValidator traineeRequestValidator) {
        this.traineeService = traineeService;
        this.traineeRequestValidator = traineeRequestValidator;
    }

    public TraineeDto create(TraineeDto traineeDto) {
        traineeRequestValidator.validateCreateRequest(traineeDto);

        return traineeService.create(traineeDto);
    }

    public TraineeDto update(Long id, TraineeDto traineeDto) {
        return traineeService.update(id, traineeDto);
    }

    public Optional<TraineeDto> getById(Long id) {
        return traineeService.getById(id);
    }

    public List<TraineeDto> getAll() {
        return traineeService.getAll();
    }

    public void delete(Long id) {
        traineeService.deleteById(id);
    }
}
