package com.gymcrm.system.service.mapper;

import com.gymcrm.system.persistance.entity.Training;
import com.gymcrm.system.persistance.repository.TraineeRepository;
import com.gymcrm.system.persistance.repository.TrainerRepository;
import com.gymcrm.system.persistance.repository.TrainingTypeRepository;
import com.gymcrm.system.service.dto.TrainingDto;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {
    private final TrainingTypeRepository trainingTypeRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    private final TrainingTypeMapper trainingTypeMapper;
    private final TraineeMapper traineeMapper;
    private final TrainerMapper trainerMapper;

    public TrainingMapper(TrainingTypeRepository trainingTypeRepository,
                          TraineeRepository traineeRepository,
                          TrainerRepository trainerRepository,
                          TrainingTypeMapper trainingTypeMapper,
                          TraineeMapper traineeMapper,
                          TrainerMapper trainerMapper) {

        this.trainingTypeRepository = trainingTypeRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.trainingTypeMapper = trainingTypeMapper;
        this.traineeMapper = traineeMapper;
        this.trainerMapper = trainerMapper;
    }


    public TrainingDto toDto(Training training) {
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(training.getId());
        trainingDto.setTrainee(traineeRepository.findById(training.getTraineeId()).isPresent() ? traineeMapper.toDto(traineeRepository.findById(training.getTraineeId()).get()) : null);
        trainingDto.setTrainer(trainerRepository.findById(training.getTrainerId()).isPresent() ? trainerMapper.toDto(trainerRepository.findById(training.getTrainerId()).get()) : null);
        trainingDto.setTrainingName(training.getTrainingName());
        trainingDto.setTrainingType(trainingTypeRepository.findById(training.getTrainingTypeId()).isPresent() ? trainingTypeMapper.toDto(trainingTypeRepository.findById(training.getTrainingTypeId()).get()) : null);
        trainingDto.setTrainingDate(training.getTrainingDate());
        trainingDto.setTrainingDuration(training.getTrainingDuration());
        return trainingDto;
    }

    public Training toEntity(TrainingDto trainingDto) {
        Training training = new Training();
        training.setTraineeId(trainingDto.getTrainee().getId());
        training.setTrainerId(trainingDto.getTrainer().getId());
        training.setTrainingName(trainingDto.getTrainingName());
        training.setTrainingTypeId(trainingDto.getTrainingType().getId());
        training.setTrainingDate(trainingDto.getTrainingDate());
        training.setTrainingDuration(trainingDto.getTrainingDuration());
        return training;
    }
}
