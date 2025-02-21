package com.gymcrm.system.service;

import com.gymcrm.system.persistance.entity.Trainee;
import com.gymcrm.system.persistance.repository.TraineeRepository;
import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.exception.EntityNotFoundException;
import com.gymcrm.system.service.mapper.TraineeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraineeService.class);

    private final TraineeRepository traineeRepository;
    private final UserService userService;
    private final TraineeMapper traineeMapper;

    public TraineeService(TraineeRepository traineeRepository, UserService userService, TraineeMapper traineeMapper) {
        this.traineeRepository = traineeRepository;
        this.userService = userService;
        this.traineeMapper = traineeMapper;
    }

    public TraineeDto create(TraineeDto traineeDto) {
        UserDto userDto = userService.create(traineeDto.getUser());
        Trainee trainee = traineeMapper.toEntity(traineeDto, userDto);
        LOGGER.info("Trainee created successfully: {}", trainee);

        return traineeMapper.toDto(traineeRepository.save(trainee));
    }

    public TraineeDto update(Long id, TraineeDto traineeDto) {
        return traineeRepository.findById(id)
                .map(trainee -> {
                    trainee.setUserId(traineeDto.getUser().getId());
                    trainee.setDateOfBirth(traineeDto.getDateOfBirth());
                    trainee.setAddress(traineeDto.getAddress());
                    LOGGER.info("Trainee with id {} updated successfully", id);

                    return traineeRepository.save(trainee);
                }).map(traineeMapper::toDto)
                .orElseThrow(() -> {
                    LOGGER.error("Trainee with id {} not found", id);
                    return new EntityNotFoundException("Trainee not found");
                });
    }

    public Optional<TraineeDto> getById(Long id) {
        LOGGER.info("Fetching trainee by id: {}", id);
        return traineeRepository.findById(id).map(traineeMapper::toDto);
    }

    public List<TraineeDto> getAll() {
        return traineeRepository.findAll()
                .stream()
                .map(traineeMapper::toDto)
                .toList();
    }

    public void deleteById(Long id) {
        traineeRepository.deleteById(id);
        LOGGER.info("Trainee with id {} deleted successfully", id);
    }
}
