package com.gymcrm.system.service.dto;

public class UserDto {
    private Long id;
    private String fistName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;

    public UserDto() {
    }

    public UserDto(String fistName, String lastName, boolean isActive) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.isActive = isActive;
    }

    public UserDto(String fistName, String lastName, String username, String password, boolean isActive) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    public UserDto(Long id, String fistName, String lastName, String username, String password, boolean isActive) {
        this.id = id;
        this.fistName = fistName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
