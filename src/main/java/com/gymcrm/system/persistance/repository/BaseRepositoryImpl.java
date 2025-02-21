package com.gymcrm.system.persistance.repository;

import com.gymcrm.system.persistance.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {

    protected final Map<Long, T> storage;

    protected BaseRepositoryImpl(Map<Long, T> storage) {
        this.storage = storage;
    }

    @Override
    public T save(T entity) {
        final Long id = entity.getId();
        if (storage.containsKey(id)) {
            storage.put(id, entity);
        } else {
            entity.setId(generateNewId());
            storage.put(entity.getId(), entity);
        }
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }

    protected Long generateNewId() {
        long highestId = storage.keySet().stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0L);

        return (++highestId);
    }
}
