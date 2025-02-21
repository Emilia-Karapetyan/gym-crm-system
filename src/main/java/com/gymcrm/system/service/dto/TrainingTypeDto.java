package com.gymcrm.system.service.dto;

public class TrainingTypeDto {
    private Long id;
    private String name;

    public TrainingTypeDto() {
    }

    public TrainingTypeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TrainingTypeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
