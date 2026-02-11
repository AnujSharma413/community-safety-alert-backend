package com.community.safetyalert.service;

import com.community.safetyalert.dto.user.UserRequestDTO;
import com.community.safetyalert.dto.user.UserResponseDTO;
import com.community.safetyalert.model.User;

import java.util.List;

public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();
}
