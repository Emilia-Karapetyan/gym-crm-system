package com.gymcrm.system.service.dto;

import java.time.LocalDate;

public class TraineeDto {
    private Long id;
    private UserDto user;
    private LocalDate dateOfBirth;
    private String address;

    public TraineeDto() {

    }

    public TraineeDto(UserDto user, LocalDate dateOfBirth, String address) {
        this.user = user;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public TraineeDto(Long id, UserDto user, LocalDate dateOfBirth, String address) {
        this.id = id;
        this.user = user;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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
        return "TraineeDto{" +
                "id=" + id +
                ", user=" + user +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                '}';
    }
}
