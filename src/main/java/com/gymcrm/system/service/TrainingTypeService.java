package com.gymcrm.system.service;

import com.gymcrm.system.persistance.entity.TrainingType;
import com.gymcrm.system.persistance.repository.TrainingTypeRepository;
import com.gymcrm.system.service.dto.TrainingTypeDto;
import com.gymcrm.system.service.mapper.TrainingTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingTypeService.class);

    private final TrainingTypeRepository trainingTypeRepository;
    private final TrainingTypeMapper trainingTypeMapper;

    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository, TrainingTypeMapper trainingTypeMapper) {
        this.trainingTypeRepository = trainingTypeRepository;
        this.trainingTypeMapper = trainingTypeMapper;
    }

    public TrainingTypeDto create(TrainingTypeDto trainingTypeDto) {
        TrainingType trainingType = trainingTypeMapper.toEntity(trainingTypeDto);
        LOGGER.info("Training type created successfully: {}", trainingType);

        return trainingTypeMapper.toDto(trainingTypeRepository.save(trainingType));
    }

    public Optional<TrainingTypeDto> getById(Long id) {
        LOGGER.info("Fetching training type by id: {}", id);
        return trainingTypeRepository.findById(id).map(trainingTypeMapper::toDto);
    }

    public List<TrainingTypeDto> getAll() {
        return trainingTypeRepository.findAll()
                .stream()
                .map(trainingTypeMapper::toDto)
                .toList();
    }

}
