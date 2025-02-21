package com.gymcrm.system.unit.loader;

import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.dataloader.impl.UserDataLoader;
import com.gymcrm.system.persistance.entity.User;
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

class UserDataLoaderTest {

    private UserDataLoader dataLoader;
    private String resourceFilePath;

    @BeforeEach
    void setUp() {
        dataLoader = new UserDataLoader();
        resourceFilePath = "src/test/resources/data/users.txt";
        ReflectionTestUtils.setField(dataLoader, "filePath", resourceFilePath);
    }

    @Test
    void loadData_ShouldLoadUsersIntoStorage() {
        Map<Long, User> storage = new HashMap<>();

        dataLoader.loadData(storage);

        assertEquals(6, storage.size());

        User user1 = storage.get(1L);
        User user2 = storage.get(2L);
        User user3 = storage.get(3L);
        User user4 = storage.get(4L);
        User user5 = storage.get(5L);
        User user6 = storage.get(6L);

        assertNotNull(user1);
        assertEquals("John", user1.getFistName());
        assertEquals("Doe", user1.getLastName());
        assertEquals("john.doe", user1.getUsername());
        assertEquals("password123", user1.getPassword());
        assertTrue(user1.isActive());

        assertNotNull(user2);
        assertEquals("Jane", user2.getFistName());
        assertEquals("Smith", user2.getLastName());
        assertEquals("janes.mith", user2.getUsername());
        assertEquals("mysecurepass", user2.getPassword());
        assertTrue(user2.isActive());

        assertNotNull(user3);
        assertEquals("Mike", user3.getFistName());
        assertEquals("Johnson", user3.getLastName());
        assertEquals("mike.trainer", user3.getUsername());
        assertEquals("trainhard", user3.getPassword());
        assertTrue(user3.isActive());

        assertNotNull(user4);
        assertEquals("Sarah", user4.getFistName());
        assertEquals("Lee", user4.getLastName());
        assertEquals("sarah.yoga", user4.getUsername());
        assertEquals("relaxandstretch", user4.getPassword());
        assertTrue(user4.isActive());

        assertNotNull(user5);
        assertEquals("Emily", user5.getFistName());
        assertEquals("Brown", user5.getLastName());
        assertEquals("emily.pilates", user5.getUsername());
        assertEquals("corefocus", user5.getPassword());
        assertTrue(user5.isActive());

        assertNotNull(user6);
        assertEquals("Daniel", user6.getFistName());
        assertEquals("White", user6.getLastName());
        assertEquals("daniel.pilates", user6.getUsername());
        assertEquals("reformer123", user6.getPassword());
        assertTrue(user6.isActive());
    }

    @Test
    void loadData_ShouldHandleException() {
        Map<Long, User> storage = new HashMap<>();

        try (MockedStatic<FileReader> mockedFileReader = mockStatic(FileReader.class)) {
            mockedFileReader.when(() -> FileReader.read(eq(resourceFilePath), any()))
                    .thenThrow(new RuntimeException("Test Exception"));

            assertDoesNotThrow(() -> dataLoader.loadData(storage));
        }
    }

    @Test
    void getLoaderType_ShouldReturnUserLoaderType() {
        assertEquals(StorageLoaderType.USER, dataLoader.getLoaderType());
    }
}
