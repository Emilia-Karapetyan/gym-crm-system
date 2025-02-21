package com.gymcrm.system.persistance.repository;

import com.gymcrm.system.persistance.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserRepository extends BaseRepositoryImpl<User> {

    protected UserRepository(Map<Long, User> storage) {
        super(storage);
    }

    public Set<String> findAllUsernames() {
        if (storage.isEmpty()) {
            return Set.of();
        }
        return storage.values().stream()
                .map(User::getUsername)
                .collect(Collectors.toSet());
    }
}
