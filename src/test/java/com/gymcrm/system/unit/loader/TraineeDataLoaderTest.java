package com.gymcrm.system.unit.loader;

import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.dataloader.impl.TraineeDataLoader;
import com.gymcrm.system.persistance.entity.Trainee;
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

public class TraineeDataLoaderTest {
    private TraineeDataLoader dataLoader;
    private String resourceFilePath;

    @BeforeEach
    void setUp() {
        dataLoader = new TraineeDataLoader();
        resourceFilePath = "src/test/resources/data/trainee.txt";
        ReflectionTestUtils.setField(dataLoader, "filePath", resourceFilePath);
    }

    @Test
    void loadData_ShouldLoadTraineesIntoStorage() {
        Map<Long, Trainee> storage = new HashMap<>();

        dataLoader.loadData(storage);

        assertEquals(3, storage.size());
        assertEquals("123 Street", storage.get(1L).getAddress());
        assertEquals("456 Avenue", storage.get(2L).getAddress());
        assertEquals("123 Fitness St", storage.get(3L).getAddress());
    }

    @Test
    void loadData_ShouldHandleException() {
        Map<Long, Trainee> storage = new HashMap<>();

        try (MockedStatic<FileReader> mockedFileReader = mockStatic(FileReader.class)) {
            mockedFileReader.when(() -> FileReader.read(eq(resourceFilePath), any()))
                    .thenThrow(new RuntimeException("Test Exception"));

            assertDoesNotThrow(() -> dataLoader.loadData(storage));
        }
    }

    @Test
    void getLoaderType_ShouldReturnTraineeLoaderType() {
        assertEquals(StorageLoaderType.TRAINEE, dataLoader.getLoaderType());
    }
}
