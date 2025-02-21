package com.gymcrm.system.config;

import com.gymcrm.system.persistance.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.gymcrm.system")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Map<Long, User> userStorageMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, Trainee> traineeStorageMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, Trainer> trainerStorageMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, Training> trainingStorageMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, TrainingType> trainingTypeStorageMap() {
        return new HashMap<>();
    }
}
