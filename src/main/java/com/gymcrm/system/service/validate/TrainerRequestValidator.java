package com.gymcrm.system.service.validate;

import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.exception.RequestInvalidParamException;
import org.springframework.stereotype.Component;

@Component
public class TrainerRequestValidator {
    public void validateCreateRequest(TrainerDto dto) {
        if (dto == null) {
            throw new RequestInvalidParamException("Trainer dto is null");
        }

        if (dto.getUser() == null || dto.getUser().getId() == null) {
            throw new RequestInvalidParamException("User id is null");
        }
    }
}
