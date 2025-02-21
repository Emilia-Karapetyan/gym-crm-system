package com.gymcrm.system.service;

import com.gymcrm.system.persistance.entity.User;
import com.gymcrm.system.persistance.repository.UserRepository;
import com.gymcrm.system.service.dto.UserDto;
import com.gymcrm.system.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.gymcrm.system.service.util.UserCredentialGenerator.generateRandomPassword;
import static com.gymcrm.system.service.util.UserCredentialGenerator.generateUsername;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto create(UserDto userDto) {
        Set<String> allUserNames = userRepository.findAllUsernames();
        String username = generateUsername(userDto.getFistName(), userDto.getLastName(), allUserNames);
        String password = generateRandomPassword();

        userDto.setUsername(username);
        userDto.setPassword(password);
        User user = userMapper.toEntity(userDto);
        LOGGER.info("User created successfully: {}", user);

        return userMapper.toDto(userRepository.save(user));
    }

    public Optional<UserDto> getById(Long id) {
        LOGGER.info("Fetching user by id: {}", id);
        return userRepository.findById(id).map(userMapper::toDto);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
