package com.onlinebookstore.OnlineBookstore.DTO.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
    @NotBlank(message="Field must not be empty")
    @Size(min =4, max = 12)
    private String username;
    @NotBlank(message="Field must not be empty")
    @Email(message="Invalid email format")
    private String email;
    @NotBlank(message="Field must not be empty")
    @Size(min = 8)
    private String password;
    private String firstName;
    private String lastName;

    public UserRegisterDTO(){}

    public UserRegisterDTO(String username, String email, String password, String firstName, String lastName){
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
