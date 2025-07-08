package com.onlinebookstore.OnlineBookstore.service;

import com.onlinebookstore.OnlineBookstore.model.User;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserInfoDTO;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserRegisterDTO;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserLoginDTO;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserUpdateDTO;
import java.util.Optional;

public interface UserService {
    UserInfoDTO registerUser(UserRegisterDTO registerDTO);
    boolean checkEmailExists(String email);
    boolean checkUsernameExists(String username);
    UserInfoDTO getCurrentUser(String usernameOrEmail);
    UserInfoDTO validateLoginCredentials(UserLoginDTO userLoginDTO);
    UserInfoDTO updateUserInfo(UserUpdateDTO userUpdateDTO);
}
