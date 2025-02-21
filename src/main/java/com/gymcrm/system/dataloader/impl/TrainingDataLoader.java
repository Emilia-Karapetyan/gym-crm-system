package com.gymcrm.system.dataloader.impl;

import com.gymcrm.system.dataloader.DataLoader;
import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.persistance.entity.Training;
import com.gymcrm.system.service.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_INFRASTRUCTURE;

@Component
@Role(ROLE_INFRASTRUCTURE)
public class TrainingDataLoader implements DataLoader<Training> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingDataLoader.class);

    @Value("${data.file.training}")
    private String filePath;

    @Override
    public void loadData(Map<Long, Training> storage) {
        LOGGER.info("Loading training data from {}", filePath);

        try {
            List<Training> trainingsFromFile = FileReader.read(filePath, line -> {
                String[] parts = line.split(",");

                Long id = Long.parseLong(parts[0].trim());
                Long traineeId = Long.parseLong(parts[1].trim());
                Long trainerId = Long.parseLong(parts[2].trim());
                String trainingName = parts[3].trim();
                Long trainingTypeId = Long.parseLong(parts[4].trim());
                LocalDate localDate = LocalDate.parse(parts[5].trim());
                String trainingDuration = parts[6].trim();

                return new Training(id, traineeId, trainerId, trainingName, trainingTypeId, localDate, trainingDuration);
            });
            trainingsFromFile.forEach(training -> storage.put(training.getId(), training));
        } catch (Exception e) {
            LOGGER.error("Error during the load training data: {}", e.getMessage());
        }
    }

    @Override
    public StorageLoaderType getLoaderType() {
        return StorageLoaderType.TRAINING;
    }
}
