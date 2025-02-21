package com.gymcrm.system.service.util;

import com.gymcrm.system.persistance.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class FileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

    private FileReader() {
    }

    public static <T extends BaseEntity> List<T> read(String filePath, Function<String, T> supplier) {
        final List<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                entities.add(supplier.apply(line));
            }
            LOGGER.info("Successfully read lines from file: {}", filePath);
        } catch (IOException e) {
            LOGGER.error("Error during the file reading :{}", e.getMessage());
        }

        return entities;
    }
}
