package com.gymcrm.system.service.mapper;

import com.gymcrm.system.persistance.entity.Trainer;
import com.gymcrm.system.persistance.repository.UserRepository;
import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public TrainerMapper(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public TrainerDto toDto(Trainer trainer) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(trainer.getId());
        trainerDto.setUser(userRepository.findById(trainer.getUserId()).isPresent() ? userMapper.toDto(userRepository.findById(trainer.getUserId()).get()) : null);
        trainerDto.setSpecialization(trainer.getSpecialization());
        return trainerDto;
    }

    public Trainer toEntity(TrainerDto trainerDto, UserDto userDto) {
        Trainer trainer = new Trainer();
        trainer.setUserId(userDto.getId());
        trainer.setSpecialization(trainerDto.getSpecialization());
        return trainer;
    }
}
