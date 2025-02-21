package com.gymcrm.system.dataloader;

import com.gymcrm.system.persistance.entity.BaseEntity;

import java.util.Map;

public interface DataLoader<T extends BaseEntity> {

    void loadData(Map<Long, T> storage);

    StorageLoaderType getLoaderType();
}
