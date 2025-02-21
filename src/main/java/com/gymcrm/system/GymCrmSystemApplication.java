package com.gymcrm.system;

import com.gymcrm.system.config.ApplicationConfig;
import com.gymcrm.system.controller.TraineeController;
import com.gymcrm.system.controller.TrainerController;
import com.gymcrm.system.controller.TrainingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GymCrmSystemApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(GymCrmSystemApplication.class);

    public static void main(String[] args) {

        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        TrainerController trainerController = context.getBean(TrainerController.class);
        TraineeController traineeController = context.getBean(TraineeController.class);
        TrainingController trainingController = context.getBean(TrainingController.class);

        LOGGER.info("Getting created trainers: {}", trainerController.getAll());
        LOGGER.info("Getting created trainees: {}", traineeController.getAll());
        LOGGER.info("Getting created trainings: {}", trainingController.getAll());

        context.close();
    }
}