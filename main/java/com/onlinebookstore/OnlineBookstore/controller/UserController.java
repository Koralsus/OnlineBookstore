package com.onlinebookstore.OnlineBookstore.controller;

import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserInfoDTO;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserLoginDTO;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserRegisterDTO;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserUpdateDTO;
import com.onlinebookstore.OnlineBookstore.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserInfoDTO> registerUser(@Valid @RequestBody UserRegisterDTO dto){
        UserInfoDTO newUser = userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserInfoDTO> validateLoginCredentials(@Valid @RequestBody UserLoginDTO dto){
        UserInfoDTO existingUser = userService.validateLoginCredentials(dto);
        return ResponseEntity.ok(existingUser);
    }

    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username){
        return ResponseEntity.ok(userService.checkUsernameExists(username));
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.checkEmailExists(email));
    }

    @PutMapping("/update")
    public ResponseEntity<UserInfoDTO> updatedUserInfo(@RequestBody UserUpdateDTO dto){
        UserInfoDTO updatedUser = userService.updateUserInfo(dto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/me")
    public UserInfoDTO getCurrentUser(@AuthenticationPrincipal Authentication authentication) {
        String username = authentication.getName();
        return userService.getCurrentUser(username);
    }
}
