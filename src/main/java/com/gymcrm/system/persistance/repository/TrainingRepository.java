package com.gymcrm.system.persistance.repository;

import com.gymcrm.system.persistance.entity.Training;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainingRepository extends BaseRepositoryImpl<Training> {

    protected TrainingRepository(Map<Long, Training> storage) {
        super(storage);
    }
}
