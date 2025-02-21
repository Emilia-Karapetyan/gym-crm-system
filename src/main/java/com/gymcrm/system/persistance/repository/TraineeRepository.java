package com.gymcrm.system.persistance.repository;

import com.gymcrm.system.persistance.entity.Trainee;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TraineeRepository extends BaseRepositoryImpl<Trainee> {

    protected TraineeRepository(Map<Long, Trainee> storage) {
        super(storage);
    }
}
