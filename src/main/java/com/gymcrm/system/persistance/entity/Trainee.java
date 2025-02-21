package com.gymcrm.system.persistance.entity;

import java.time.LocalDate;

public class Trainee extends BaseEntity {

    private Long userId;
    private LocalDate dateOfBirth;
    private String address;

    public Trainee() {
    }

    public Trainee(Long id, Long userId, LocalDate dateOfBirth, String address) {
        setId(id);
        this.userId = userId;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + getId() +
                "userId=" + userId +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                '}';
    }
}
