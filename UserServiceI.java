package com.onlinebookstore.OnlineBookstore.service.implementations;

import com.onlinebookstore.OnlineBookstore.model.Role;
import com.onlinebookstore.OnlineBookstore.service.UserService;
import com.onlinebookstore.OnlineBookstore.repository.UserRepository;
import com.onlinebookstore.OnlineBookstore.repository.RoleRepository;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserRegisterDTO;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserInfoDTO;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserLoginDTO;
import com.onlinebookstore.OnlineBookstore.DTO.UserDTO.UserUpdateDTO;
import com.onlinebookstore.OnlineBookstore.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceI implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceI(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserInfoDTO registerUser(UserRegisterDTO dto){
        if (checkEmailExists(dto.getEmail())){
            throw new IllegalArgumentException("Email already exists!");
        }
        if (checkUsernameExists(dto.getUsername())){
            throw new IllegalArgumentException("Username already exists!");
        }

        User user = new User();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        user.setRoles(Collections.singleton(userRole));
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        User newUser = userRepository.save(user);
        return mapToDTO(newUser);
    }

    @Override
    public UserInfoDTO validateLoginCredentials(UserLoginDTO dto){
        String userOrEmail = dto.getUsernameOrEmail();
        String password = dto.getPassword();

        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(userOrEmail, userOrEmail);

        if (optionalUser.isEmpty() || !passwordEncoder.matches(password, optionalUser.get().getPasswordHash())){
            throw new IllegalArgumentException("Invalid username or password");
        }

        return mapToDTO(optionalUser.get());
    }

    @Override
    public boolean checkEmailExists(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkUsernameExists(String username){
        return userRepository.existsByUsername(username);
    }

    @Transactional
    @Override
    public UserInfoDTO updateUserInfo(UserUpdateDTO dto){
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(dto.getUsernameOrEmail(), dto.getUsernameOrEmail());

        if (optionalUser.isEmpty()){
            throw new IllegalArgumentException("User not found");
        }

        User user = optionalUser.get();

        if (dto.getNewUsername() != null && !dto.getNewUsername().equals(user.getUsername())) {
            if (checkUsernameExists(dto.getNewUsername())) {
                throw new IllegalArgumentException("Username already exists!");
            }
            user.setUsername(dto.getNewUsername());
        }

        if (dto.getNewEmail() != null && !dto.getNewEmail().equals(user.getEmail())) {
            if (checkEmailExists(dto.getNewEmail())) {
                throw new IllegalArgumentException("Email already exists!");
            }
            user.setEmail(dto.getNewEmail());
        }

        if (dto.getNewFirstName() != null) {
            user.setFirstName(dto.getNewFirstName());
        }

        if (dto.getNewLastName() != null) {
            user.setLastName(dto.getNewLastName());
        }

        if (dto.getNewPassword() != null) {
            user.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
        }

        User updatedUser = userRepository.save(user);
        return mapToDTO(updatedUser);
    }

    @Override
    public UserInfoDTO getCurrentUser(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return mapToDTO(user);
    }


    private UserInfoDTO mapToDTO(User user) {
        return new UserInfoDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreatedAt()
        );
    }
}
