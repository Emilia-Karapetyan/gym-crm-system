package com.gymcrm.system.unit.service.validator;

import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.exception.RequestInvalidParamException;
import com.gymcrm.system.service.validate.TraineeRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TraineeRequestValidatorTest {
    private TraineeRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TraineeRequestValidator();
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenDtoIsNull() {
        Exception exception = assertThrows(RequestInvalidParamException.class,
                () -> validator.validateCreateRequest(null));
        assertEquals("Trainee dto is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenUserIsNull() {
        TraineeDto dto = new TraineeDto();
        dto.setUser(null);

        Exception exception = assertThrows(RequestInvalidParamException.class,
                () -> validator.validateCreateRequest(dto));
        assertEquals("User id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenUserIdIsNull() {
        TraineeDto dto = new TraineeDto();
        UserDto user = new UserDto();
        user.setId(null);
        dto.setUser(user);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("User id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldNotThrowException_WhenDtoIsValid() {
        TraineeDto dto = new TraineeDto();
        UserDto user = new UserDto();
        user.setId(1L);
        dto.setUser(user);

        assertDoesNotThrow(() -> validator.validateCreateRequest(dto));
    }

}
