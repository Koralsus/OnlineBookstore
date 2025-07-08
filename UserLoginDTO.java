package com.onlinebookstore.OnlineBookstore.DTO.UserDTO;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {
    @NotBlank(message="Field must not be empty")
    private String usernameOrEmail;
    @NotBlank(message="Field must not be empty")
    private String password;

    public UserLoginDTO(){}

    public UserLoginDTO(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}