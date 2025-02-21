package com.gymcrm.system.service.mapper;

import com.gymcrm.system.persistance.entity.TrainingType;
import com.gymcrm.system.service.dto.TrainingTypeDto;
import org.springframework.stereotype.Component;

@Component
public class TrainingTypeMapper {

    public TrainingTypeDto toDto(TrainingType trainingType) {
        TrainingTypeDto trainingTypeDto = new TrainingTypeDto();
        trainingTypeDto.setId(trainingType.getId());
        trainingTypeDto.setName(trainingType.getName());
        return trainingTypeDto;
    }

    public TrainingType toEntity(TrainingTypeDto trainingTypeDto) {
        TrainingType trainingType = new TrainingType();
        trainingType.setName(trainingTypeDto.getName());
        return trainingType;
    }
}
