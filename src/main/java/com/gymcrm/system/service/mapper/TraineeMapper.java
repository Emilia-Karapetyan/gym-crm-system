package com.gymcrm.system.service.mapper;

import com.gymcrm.system.persistance.entity.Trainee;
import com.gymcrm.system.persistance.repository.UserRepository;
import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class TraineeMapper {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public TraineeMapper(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public TraineeDto toDto(Trainee trainee) {
        TraineeDto traineeDto = new TraineeDto();
        traineeDto.setId(trainee.getId());
        traineeDto.setUser(userRepository.findById(trainee.getUserId()).isPresent() ? userMapper.toDto(userRepository.findById(trainee.getUserId()).get()) : null);
        traineeDto.setDateOfBirth(trainee.getDateOfBirth());
        traineeDto.setAddress(trainee.getAddress());
        return traineeDto;
    }

    public Trainee toEntity(TraineeDto traineeDto, UserDto userDto) {
        Trainee trainee = new Trainee();
        trainee.setUserId(userDto.getId());
        trainee.setDateOfBirth(traineeDto.getDateOfBirth());
        trainee.setAddress(traineeDto.getAddress());
        return trainee;
    }
}
