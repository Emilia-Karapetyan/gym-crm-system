package com.gymcrm.system.service.dto;

import java.time.LocalDate;

public class TrainingDto {
    private Long id;
    private TraineeDto trainee;
    private TrainerDto trainer;
    private String trainingName;
    private TrainingTypeDto trainingType;
    private LocalDate trainingDate;
    private String trainingDuration;

    public TrainingDto() {
    }

    public TrainingDto(TraineeDto trainee, TrainerDto trainer, String trainingName, TrainingTypeDto trainingType, LocalDate trainingDate, String trainingDuration) {
        this.trainee = trainee;
        this.trainer = trainer;
        this.trainingName = trainingName;
        this.trainingType = trainingType;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TraineeDto getTrainee() {
        return trainee;
    }

    public void setTrainee(TraineeDto trainee) {
        this.trainee = trainee;
    }

    public TrainerDto getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerDto trainer) {
        this.trainer = trainer;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public TrainingTypeDto getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingTypeDto trainingType) {
        this.trainingType = trainingType;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(String trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    @Override
    public String toString() {
        return "TrainingDto{" +
                "id=" + id +
                ", trainee=" + trainee +
                ", trainer=" + trainer +
                ", trainingName='" + trainingName + '\'' +
                ", trainingType=" + trainingType +
                ", trainingDate=" + trainingDate +
                ", trainingDuration='" + trainingDuration + '\'' +
                '}';
    }
}
