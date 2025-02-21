package com.gymcrm.system.dataloader.impl;

import com.gymcrm.system.dataloader.DataLoader;
import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.persistance.entity.Trainee;
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
public class TraineeDataLoader implements DataLoader<Trainee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TraineeDataLoader.class);

    @Value("${data.file.trainee}")
    private String filePath;

    @Override
    public void loadData(Map<Long, Trainee> storage) {
        LOGGER.info("Loading trainee data from {}", filePath);

        try {
            List<Trainee> list = FileReader.read(filePath, line -> {
                String[] parts = line.split(",");
                long id = Long.parseLong(parts[0].trim());
                long userId = Long.parseLong(parts[1].trim());
                LocalDate localDate = LocalDate.parse(parts[2].trim());
                String address = parts[3].trim();

                return new Trainee(id, userId, localDate, address);
            });
            list.forEach(trainee -> storage.put(trainee.getId(), trainee));
        } catch (Exception e) {
            LOGGER.error("Error during the load trainee data: {}", e.getMessage());
        }
    }

    @Override
    public StorageLoaderType getLoaderType() {
        return StorageLoaderType.TRAINEE;
    }
}
