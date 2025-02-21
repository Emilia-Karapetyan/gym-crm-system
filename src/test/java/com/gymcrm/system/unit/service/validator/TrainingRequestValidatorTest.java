package com.gymcrm.system.unit.service.validator;

import com.gymcrm.system.service.dto.TraineeDto;
import com.gymcrm.system.service.dto.TrainerDto;
import com.gymcrm.system.service.dto.TrainingDto;
import com.gymcrm.system.service.dto.TrainingTypeDto;
import com.gymcrm.system.service.exception.RequestInvalidParamException;
import com.gymcrm.system.service.validate.TrainingRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingRequestValidatorTest {
    private TrainingRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TrainingRequestValidator();
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenDtoIsNull() {
        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(null));
        assertEquals("Training dto is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenTraineeIsNull() {
        TrainingDto dto = new TrainingDto();
        dto.setTrainee(null);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("Trainee id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenTraineeIdIsNull() {
        TrainingDto dto = new TrainingDto();
        TraineeDto trainee = new TraineeDto();
        trainee.setId(null);
        dto.setTrainee(trainee);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("Trainee id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenTrainerIsNull() {
        TrainingDto dto = new TrainingDto();
        TraineeDto trainee = new TraineeDto();
        trainee.setId(1L);
        dto.setTrainee(trainee);
        dto.setTrainer(null);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("Trainer id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenTrainerIdIsNull() {
        TrainingDto dto = new TrainingDto();
        TraineeDto trainee = new TraineeDto();
        trainee.setId(1L);
        TrainerDto trainer = new TrainerDto();
        trainer.setId(null);
        dto.setTrainee(trainee);
        dto.setTrainer(trainer);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("Trainer id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenTrainingTypeIsNull() {
        TraineeDto trainee = new TraineeDto();
        trainee.setId(1L);

        TrainerDto trainer = new TrainerDto();
        trainer.setId(2L);
        TrainingDto dto = new TrainingDto();
        dto.setTrainingType(null);
        dto.setTrainee(trainee);
        dto.setTrainer(trainer);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("Training type id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenTrainingTypeIdIsNull() {
        TraineeDto trainee = new TraineeDto();
        trainee.setId(1L);
        TrainerDto trainer = new TrainerDto();
        trainer.setId(2L);

        TrainingDto dto = new TrainingDto();
        TrainingTypeDto trainingType = new TrainingTypeDto();
        trainingType.setId(null);
        dto.setTrainingType(trainingType);
        dto.setTrainer(trainer);
        dto.setTrainee(trainee);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("Training type id is null", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldThrowException_WhenTrainingNameIsEmpty() {
        TraineeDto trainee = new TraineeDto();
        trainee.setId(1L);
        TrainerDto trainer = new TrainerDto();
        trainer.setId(2L);
        TrainingTypeDto trainingType = new TrainingTypeDto();
        trainingType.setId(3L);

        TrainingDto dto = new TrainingDto();
        dto.setTrainingName("");
        dto.setTrainer(trainer);
        dto.setTrainee(trainee);
        dto.setTrainingType(trainingType);

        Exception exception = assertThrows(RequestInvalidParamException.class, () -> validator.validateCreateRequest(dto));
        assertEquals("Training name is empty", exception.getMessage());
    }

    @Test
    void validateCreateRequest_ShouldNotThrowException_WhenDtoIsValid() {
        TrainingDto dto = new TrainingDto();
        TraineeDto trainee = new TraineeDto();
        trainee.setId(1L);
        dto.setTrainee(trainee);

        TrainerDto trainer = new TrainerDto();
        trainer.setId(2L);
        dto.setTrainer(trainer);

        TrainingTypeDto trainingType = new TrainingTypeDto();
        trainingType.setId(3L);
        dto.setTrainingType(trainingType);

        dto.setTrainingName("Strength Training");

        assertDoesNotThrow(() -> validator.validateCreateRequest(dto));
    }
}
