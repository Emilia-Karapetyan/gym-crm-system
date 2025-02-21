package com.gymcrm.system.unit.loader;

import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.dataloader.impl.TrainingDataLoader;
import com.gymcrm.system.persistance.entity.Training;
import com.gymcrm.system.service.util.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class TrainingDataLoaderTest {

    private TrainingDataLoader dataLoader;
    private String resourceFilePath;

    @BeforeEach
    void setUp() {
        dataLoader = new TrainingDataLoader();
        resourceFilePath = "src/test/resources/data/training.txt";
        ReflectionTestUtils.setField(dataLoader, "filePath", resourceFilePath);
    }

    @Test
    void loadData_ShouldLoadTrainingsIntoStorage() {
        Map<Long, Training> storage = new HashMap<>();

        dataLoader.loadData(storage);

        Training training1 = storage.get(1L);
        Training training2 = storage.get(2L);
        Training training3 = storage.get(3L);

        assertNotNull(training1);
        assertEquals(1L, training1.getTraineeId());
        assertEquals(1L, training1.getTrainerId());
        assertEquals("Full Body Workout", training1.getTrainingName());
        assertEquals(1L, training1.getTrainingTypeId());
        assertEquals(LocalDate.of(2025, 2, 10), training1.getTrainingDate());
        assertEquals("60 minutes", training1.getTrainingDuration());

        assertNotNull(training2);
        assertEquals(2L, training2.getTraineeId());
        assertEquals(2L, training2.getTrainerId());
        assertEquals("Morning Yoga", training2.getTrainingName());
        assertEquals(2L, training2.getTrainingTypeId());
        assertEquals(LocalDate.of(2025, 2, 12), training2.getTrainingDate());
        assertEquals("45 minutes", training2.getTrainingDuration());

        assertNotNull(training3);
        assertEquals(1L, training3.getTraineeId());
        assertEquals(3L, training3.getTrainerId());
        assertEquals("Math Pilates", training3.getTrainingName());
        assertEquals(3L, training3.getTrainingTypeId());
        assertEquals(LocalDate.of(2025, 2, 14), training3.getTrainingDate());
        assertEquals("50 minutes", training3.getTrainingDuration());
    }

    @Test
    void loadData_ShouldHandleException() {
        Map<Long, Training> storage = new HashMap<>();

        try (MockedStatic<FileReader> mockedFileReader = mockStatic(FileReader.class)) {
            mockedFileReader.when(() -> FileReader.read(eq(resourceFilePath), any()))
                    .thenThrow(new RuntimeException("Test Exception"));

            assertDoesNotThrow(() -> dataLoader.loadData(storage));
        }
    }

    @Test
    void getLoaderType_ShouldReturnTrainingLoaderType() {
        assertEquals(StorageLoaderType.TRAINING, dataLoader.getLoaderType());
    }
}
