package com.gymcrm.system.service.validate;

import com.gymcrm.system.service.dto.TrainingDto;
import com.gymcrm.system.service.exception.RequestInvalidParamException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TrainingRequestValidator {

    public void validateCreateRequest(TrainingDto dto) {
        if (dto == null) {
            throw new RequestInvalidParamException("Training dto is null");
        }

        if (dto.getTrainee() == null || dto.getTrainee().getId() == null) {
            throw new RequestInvalidParamException("Trainee id is null");
        }

        if (dto.getTrainer() == null || dto.getTrainer().getId() == null) {
            throw new RequestInvalidParamException("Trainer id is null");
        }

        if (dto.getTrainingType() == null || dto.getTrainingType().getId() == null) {
            throw new RequestInvalidParamException("Training type id is null");
        }

        if (!StringUtils.hasLength(dto.getTrainingName())) {
            throw new RequestInvalidParamException("Training name is empty");
        }
    }
}
