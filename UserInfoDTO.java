package com.onlinebookstore.OnlineBookstore.DTO.UserDTO;

import java.time.LocalDateTime;

public class UserInfoDTO {
    private Integer userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

    public UserInfoDTO(){}

    public UserInfoDTO(Integer userId, String username, String email, String firstName, String lastName, LocalDateTime createdAt){
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
