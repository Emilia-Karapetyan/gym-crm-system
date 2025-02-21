package com.gymcrm.system.persistance.entity;

public class Trainer extends BaseEntity {

    private Long userId;
    private String specialization;

    public Trainer() {
    }

    public Trainer(Long id, long userId, String specialization) {
        setId(id);
        this.userId = userId;
        this.specialization = specialization;
    }

    public Trainer(Long userId, String specialization) {
        this.userId = userId;
        this.specialization = specialization;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + getId() +
                ", userId=" + userId +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
