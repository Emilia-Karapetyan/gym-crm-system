package com.gymcrm.system.dataloader;

import java.util.Arrays;

public enum StorageLoaderType {
    TRAINEE("traineeStorageMap"),
    TRAINER("trainerStorageMap"),
    TRAINING("trainingStorageMap"),
    TRAINING_TYPE("trainingTypeStorageMap"),
    USER("userStorageMap");

    private final String beanName;

    StorageLoaderType(String beanName) {
        this.beanName = beanName;
    }

    public static StorageLoaderType getByBeanName(String beanName) {
        return Arrays.stream(values())
                .filter(type -> type.beanName.equals(beanName))
                .findFirst()
                .orElse(null);
    }
}
