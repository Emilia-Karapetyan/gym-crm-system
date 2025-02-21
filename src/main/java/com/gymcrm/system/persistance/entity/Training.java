package com.gymcrm.system.persistance.entity;

import java.time.LocalDate;

public class Training extends BaseEntity {

    private Long traineeId;
    private Long trainerId;
    private String trainingName;
    private Long trainingTypeId;
    private LocalDate trainingDate;
    private String trainingDuration;

    public Training() {
    }

    public Training(Long id, Long traineeId, Long trainerId, String trainingName, Long trainingTypeId, LocalDate trainingDate, String trainingDuration) {
        setId(id);
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.trainingName = trainingName;
        this.trainingTypeId = trainingTypeId;
        this.trainingDate = trainingDate;
        this.trainingDuration = trainingDuration;
    }

    public Long getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Long traineeId) {
        this.traineeId = traineeId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public Long getTrainingTypeId() {
        return trainingTypeId;
    }

    public void setTrainingTypeId(Long trainingTypeId) {
        this.trainingTypeId = trainingTypeId;
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
        return "Training{" +
                "id=" + getId() +
                ", traineeId=" + traineeId +
                ", trainerId=" + trainerId +
                ", trainingName='" + trainingName + '\'' +
                ", trainingTypeId=" + trainingTypeId +
                ", trainingDate=" + trainingDate +
                ", trainingDuration='" + trainingDuration + '\'' +
                '}';
    }
}
