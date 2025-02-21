package com.gymcrm.system.unit.service.util;

import com.gymcrm.system.service.util.UserCredentialGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserCredentialGeneratorTest {

    @Test
    void testGenerateUsername_UniqueCase() {
        Set<String> existingUsernames = new HashSet<>();
        String username = UserCredentialGenerator.generateUsername("John", "Doe", existingUsernames);
        assertEquals("John.Doe", username);
    }

    @Test
    void testGenerateUsername_WithExistingDuplicates() {
        Set<String> existingUsernames = new HashSet<>();
        existingUsernames.add("John.Doe");

        String username = UserCredentialGenerator.generateUsername("John", "Doe", existingUsernames);
        assertEquals("John.Doe_1", username);
    }

    @Test
    void testGenerateUsername_MultipleDuplicateEntries() {
        Set<String> existingUsernames = new HashSet<>();
        for (int i = 0; i <= 5; i++) {
            existingUsernames.add("John.Doe" + (i == 0 ? "" : "_" + i));
        }

        String username = UserCredentialGenerator.generateUsername("John", "Doe", existingUsernames);
        assertEquals("John.Doe_6", username);
    }
}
