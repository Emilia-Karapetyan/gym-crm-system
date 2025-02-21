package com.gymcrm.system.persistance.repository;

import com.gymcrm.system.persistance.entity.Trainer;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TrainerRepository extends BaseRepositoryImpl<Trainer> {

    public TrainerRepository(Map<Long, Trainer> trainerStorageMap) {
        super(trainerStorageMap);
    }
}
