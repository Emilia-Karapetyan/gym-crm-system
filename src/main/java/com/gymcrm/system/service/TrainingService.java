package com.gymcrm.system.service;

import com.gymcrm.system.persistance.entity.Training;
import com.gymcrm.system.persistance.repository.TrainingRepository;
import com.gymcrm.system.service.dto.TrainingDto;
import com.gymcrm.system.service.mapper.TrainingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingService.class);

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    public TrainingService(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    public TrainingDto create(TrainingDto trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        LOGGER.info("Training created successfully: {}", training);

        return trainingMapper.toDto(trainingRepository.save(training));
    }

    public Optional<TrainingDto> getById(Long id) {
        LOGGER.info("Fetching training by id: {}", id);
        return trainingRepository.findById(id).map(trainingMapper::toDto);
    }

    public List<TrainingDto> getAll() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
