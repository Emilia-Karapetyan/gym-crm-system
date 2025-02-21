package com.gymcrm.system.unit.service.validator;

import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.exception.RequestInvalidParamException;
import com.gymcrm.system.service.validate.TrainerRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerRequestValidatorTest {
    private TrainerRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TrainerRequestValidator();
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenDtoIsNull() {
        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(null));
        assertEquals("Trainer dto is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenUserIsNull() {
        TrainerDto dto = new TrainerDto();
        dto.setUser(null);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("User id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenUserIdIsNull() {
        TrainerDto dto = new TrainerDto();
        UserDto user = new UserDto();
        user.setId(null);
        dto.setUser(user);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("User id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldNotThrowException_WhenDtoIsValid() {
        TrainerDto dto = new TrainerDto();
        UserDto user = new UserDto();
        user.setId(1L);
        dto.setUser(user);

        assertDoesNotThrow(() -> validator.validateCreateRequest(dto));
    }
}
