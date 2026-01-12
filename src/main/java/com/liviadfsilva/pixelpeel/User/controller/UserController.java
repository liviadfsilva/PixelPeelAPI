package com.liviadfsilva.pixelpeel.User.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.Auth.dto.AuthenticatedUserDTO;
import com.liviadfsilva.pixelpeel.User.dto.UserRegistrationDTO;
import com.liviadfsilva.pixelpeel.User.dto.UserResponseDTO;
import com.liviadfsilva.pixelpeel.User.dto.UserUpdateDTO;
import com.liviadfsilva.pixelpeel.User.model.User;
import com.liviadfsilva.pixelpeel.User.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMe(@AuthenticationPrincipal AuthenticatedUserDTO principal) {

        User user = service.getUserById(principal.getId());

        return ResponseEntity.ok(new UserResponseDTO(user.getName(), user.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegistrationDTO dto) {
    User user = service.registerUser(dto.getName(), dto.getEmail(), dto.getPassword());

    UserResponseDTO response = new UserResponseDTO(user.getName(), user.getEmail());
    return ResponseEntity.ok(response);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponseDTO> updateUser(
        @AuthenticationPrincipal AuthenticatedUserDTO principal,
         @RequestBody UserUpdateDTO dto) {
        User user = service.updateUser(principal.getId(), dto);

        UserResponseDTO response = new UserResponseDTO(user.getName(), user.getEmail());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/me")
    public void deleteUser(@AuthenticationPrincipal AuthenticatedUserDTO principal) {
        service.softDeleteUser(principal.getId());
    }
}