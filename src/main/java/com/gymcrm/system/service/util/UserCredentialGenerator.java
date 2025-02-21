package com.gymcrm.system.service.util;

import java.security.SecureRandom;
import java.util.Set;

public final class UserCredentialGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private UserCredentialGenerator() {
    }

    public static String generateUsername(String firstName, String lastName, Set<String> existingUsernames) {
        String baseUsername = "%s.%s".formatted(firstName, lastName);
        String generatedUsername = baseUsername;
        int counter = 1;

        while (existingUsernames.contains(generatedUsername)) {
            generatedUsername = baseUsername + "_" + counter;
            counter++;
        }

        return generatedUsername;
    }

    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder(10);

        password.append(getRandomChar("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        password.append(getRandomChar("abcdefghijklmnopqrstuvwxyz"));
        password.append(getRandomChar("0123456789"));

        for (int i = 4; i < 10; i++) {
            password.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }

        return shuffleString(password.toString());
    }

    private static char getRandomChar(String source) {
        return source.charAt(RANDOM.nextInt(source.length()));
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
}
