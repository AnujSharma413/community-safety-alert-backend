package com.community.safetyalert.service.impl;

import com.community.safetyalert.dto.user.UserRequestDTO;
import com.community.safetyalert.dto.user.UserResponseDTO;
import com.community.safetyalert.model.Role;
import com.community.safetyalert.model.User;
import com.community.safetyalert.repository.UserRepo;
import com.community.safetyalert.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepo.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole().name()
        );
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepo.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole().name()
                ))
                .toList();
    }
}
