package com.gymcrm.system.dataloader.impl;

import com.gymcrm.system.dataloader.DataLoader;
import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.persistance.entity.TrainingType;
import com.gymcrm.system.service.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_INFRASTRUCTURE;

@Component
@Role(ROLE_INFRASTRUCTURE)
public class TrainingTypeDataLoader implements DataLoader<TrainingType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingTypeDataLoader.class);

    @Value("${data.file.trainingType}")
    private String filePath;

    @Override
    public void loadData(Map<Long, TrainingType> storage) {
        LOGGER.info("Loading training type data from {}", filePath);

        try {
            List<TrainingType> trainingTypes = FileReader.read(filePath, line -> {
                String[] parts = line.split(",");

                Long id = Long.parseLong(parts[0].trim());
                String name = parts[1].trim();

                return new TrainingType(id, name);
            });
            trainingTypes.forEach(user -> storage.put(user.getId(), user));

        } catch (Exception e) {
            LOGGER.error("Error during the load training type data: {}", e.getMessage());
        }
    }

    @Override
    public StorageLoaderType getLoaderType() {
        return StorageLoaderType.TRAINING_TYPE;
    }
}
