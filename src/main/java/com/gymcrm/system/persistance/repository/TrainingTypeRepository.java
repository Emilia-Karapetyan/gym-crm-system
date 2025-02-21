package com.gymcrm.system.persistance.repository;

import com.gymcrm.system.persistance.entity.TrainingType;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainingTypeRepository extends BaseRepositoryImpl<TrainingType> {
    protected TrainingTypeRepository(Map<Long, TrainingType> storage) {
        super(storage);
    }
}
