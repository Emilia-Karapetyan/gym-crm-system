package com.gymcrm.system.persistance.entity;

public class TrainingType extends BaseEntity {
    private String name;

    public TrainingType() {
    }

    public TrainingType(Long id, String name) {
        setId(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TrainingType{" +
                "name='" + name + '\'' +
                '}';
    }
}
