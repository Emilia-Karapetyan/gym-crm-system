package com.gymcrm.system.service.validate;

import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.exception.RequestInvalidParamException;
import org.springframework.stereotype.Component;

@Component
public class TraineeRequestValidator {
    public void validateCreateRequest(TraineeDto dto) {
        if (dto == null) {
            throw new RequestInvalidParamException("Trainee dto is null");
        }

        if (dto.getUser() == null || dto.getUser().getId() == null) {
            throw new RequestInvalidParamException("User id is null");
        }
    }
}
