package com.gymcrm.system.dataloader.impl;

import com.gymcrm.system.dataloader.DataLoader;
import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.persistance.entity.Trainer;
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
public class TrainerDataLoader implements DataLoader<Trainer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainerDataLoader.class);

    @Value("${data.file.trainer}")
    private String filePath;

    @Override
    public void loadData(Map<Long, Trainer> storage) {
        LOGGER.info("Loading trainer data from {}", filePath);

        try {
            List<Trainer> trainersFromFile = FileReader.read(filePath, line -> {
                String[] parts = line.split(",");
                long id = Long.parseLong(parts[0].trim());
                long userId = Long.parseLong(parts[1].trim());
                String specialization = parts[2].trim();

                return new Trainer(id, userId, specialization);
            });
            trainersFromFile.forEach(trainer -> storage.put(trainer.getId(), trainer));
        } catch (Exception e) {
            LOGGER.error("Error during the load trainer data: {}", e.getMessage());
        }
    }

    @Override
    public StorageLoaderType getLoaderType() {
        return StorageLoaderType.TRAINER;
    }
}
