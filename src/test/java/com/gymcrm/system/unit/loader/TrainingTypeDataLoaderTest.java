package com.gymcrm.system.unit.loader;

import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.dataloader.impl.TrainingTypeDataLoader;
import com.gymcrm.system.persistance.entity.TrainingType;
import com.gymcrm.system.service.util.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

class TrainingTypeDataLoaderTest {

    private TrainingTypeDataLoader dataLoader;
    private String resourceFilePath;

    @BeforeEach
    void setUp() {
        dataLoader = new TrainingTypeDataLoader();
        resourceFilePath = "src/test/resources/data/trainingtype.txt";
        ReflectionTestUtils.setField(dataLoader, "filePath", resourceFilePath);
    }

    @Test
    void loadData_ShouldLoadTrainingTypesIntoStorage() {
        Map<Long, TrainingType> storage = new HashMap<>();

        dataLoader.loadData(storage);

        assertEquals(3, storage.size());

        TrainingType type1 = storage.get(1L);
        TrainingType type2 = storage.get(2L);
        TrainingType type3 = storage.get(3L);

        assertNotNull(type1);
        assertEquals("Strength", type1.getName());

        assertNotNull(type2);
        assertEquals("Yoga", type2.getName());

        assertNotNull(type3);
        assertEquals("Pilates", type3.getName());
    }

    @Test
    void loadData_ShouldHandleException() {
        Map<Long, TrainingType> storage = new HashMap<>();

        try (MockedStatic<FileReader> mockedFileReader = mockStatic(FileReader.class)) {
            mockedFileReader.when(() -> FileReader.read(eq(resourceFilePath), any()))
                    .thenThrow(new RuntimeException("Test Exception"));

            assertDoesNotThrow(() -> dataLoader.loadData(storage));
        }
    }

    @Test
    void getLoaderType_ShouldReturnTrainingTypeLoaderType() {
        assertEquals(StorageLoaderType.TRAINING_TYPE, dataLoader.getLoaderType());
    }
}
