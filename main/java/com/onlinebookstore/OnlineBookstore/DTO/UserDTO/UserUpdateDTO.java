package com.onlinebookstore.OnlineBookstore.DTO.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO {
    @NotBlank(message = "Field must not be empty")
    private String usernameOrEmail;
    @Size(min = 4, max = 12)
    private String newUsername;
    @Email
    private String newEmail;
    private String newFirstName;
    private String newLastName;
    private String newPassword;

    public UserUpdateDTO(){}

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
