package com.gymcrm.system.dataloader.impl;

import com.gymcrm.system.dataloader.DataLoader;
import com.gymcrm.system.dataloader.StorageLoaderType;
import com.gymcrm.system.persistance.entity.User;
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
public class UserDataLoader implements DataLoader<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDataLoader.class);

    @Value("${data.file.user}")
    private String filePath;

    @Override
    public void loadData(Map<Long, User> storage) {
        LOGGER.info("Loading user data from {}", filePath);

        try {
            List<User> userList = FileReader.read(filePath, line -> {
                String[] parts = line.split(",");

                Long id = Long.parseLong(parts[0].trim());
                String firstName = parts[1].trim();
                String lastName = parts[2].trim();
                String username = parts[3].trim();
                String password = parts[4].trim();
                boolean active = Boolean.parseBoolean(parts[5].trim());
                return new User(id, firstName, lastName, username, password, active);
            });

            userList.forEach(user -> storage.put(user.getId(), user));

        } catch (Exception e) {
            LOGGER.error("Error during the load user data: {}", e.getMessage());
        }
    }

    @Override
    public StorageLoaderType getLoaderType() {
        return StorageLoaderType.USER;
    }
}
