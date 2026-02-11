package com.community.safetyalert.controller;

import com.community.safetyalert.dto.user.UserRequestDTO;
import com.community.safetyalert.dto.user.UserResponseDTO;
import com.community.safetyalert.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        return userService.registerUser(userRequestDTO);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserId(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }
}
