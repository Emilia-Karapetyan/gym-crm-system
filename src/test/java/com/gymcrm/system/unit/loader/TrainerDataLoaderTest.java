package com.gymcrm.system.unit.loader;

import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.dataloader.impl.TrainerDataLoader;
import com.gymcrm.system.persistance.entity.Trainer;
import com.gymcrm.system.service.util.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class TrainerDataLoaderTest {

    private TrainerDataLoader dataLoader;
    private String resourceFilePath;

    @BeforeEach
    void setUp() {
        dataLoader = new TrainerDataLoader();
        resourceFilePath = "src/test/resources/data/trainer.txt";
        ReflectionTestUtils.setField(dataLoader, "filePath", resourceFilePath);
    }

    @Test
    void loadData_ShouldLoadTrainersIntoStorage() {
        Map<Long, Trainer> storage = new HashMap<>();

        dataLoader.loadData(storage);

        assertEquals(3, storage.size());
        assertEquals("Strength trainer", storage.get(1L).getSpecialization());
        assertEquals("Yoga & Flexibility trainer", storage.get(2L).getSpecialization());
        assertEquals("Pilates trainer", storage.get(3L).getSpecialization());
    }

    @Test
    void loadData_ShouldHandleException() {
        Map<Long, Trainer> storage = new HashMap<>();

        try (MockedStatic<FileReader> mockedFileReader = mockStatic(FileReader.class)) {
            mockedFileReader.when(() -> FileReader.read(eq(resourceFilePath), any()))
                    .thenThrow(new RuntimeException("Test Exception"));

            assertDoesNotThrow(() -> dataLoader.loadData(storage));
        }
    }

    @Test
    void getLoaderType_ShouldReturnTrainerLoaderType() {
        assertEquals(StorageLoaderType.TRAINER, dataLoader.getLoaderType());
    }
}
