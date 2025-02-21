package com.gymcrm.system.service.dto;

public class TrainerDto {
    private Long id;
    private UserDto user;
    private String specialization;

    public TrainerDto() {
    }

    public TrainerDto(Long id, UserDto user, String specialization) {
        this.id = id;
        this.user = user;
        this.specialization = specialization;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "TrainerDto{" +
                "id=" + id +
                ", user=" + user +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
