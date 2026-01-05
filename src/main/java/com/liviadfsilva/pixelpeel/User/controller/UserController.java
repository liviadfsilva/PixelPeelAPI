package com.liviadfsilva.pixelpeel.User.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liviadfsilva.pixelpeel.User.dto.UserRegistrationDTO;
import com.liviadfsilva.pixelpeel.User.dto.UserResponseDTO;
import com.liviadfsilva.pixelpeel.User.dto.UserUpdateDTO;
import com.liviadfsilva.pixelpeel.User.model.Role;
import com.liviadfsilva.pixelpeel.User.model.User;
import com.liviadfsilva.pixelpeel.User.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
    
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@RequestParam String email) {
        return service.getUserByEmail(email);
    }

    @GetMapping("/role/{role}")
    public List<User> getUserByRole(@RequestParam Role role) {
        return service.getUserByRole(role);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegistrationDTO dto) {
    User user = service.registerUser(dto.getName(), dto.getEmail(), dto.getPassword());

    UserResponseDTO response = new UserResponseDTO(user.getName(), user.getEmail());
    return ResponseEntity.ok(response);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<UserResponseDTO> createAdmin(@RequestBody UserRegistrationDTO dto) {
    User user = service.createAdmin(dto.getName(), dto.getEmail(), dto.getPassword());

    UserResponseDTO response = new UserResponseDTO(user.getName(), user.getEmail());
    return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        User user = service.updateUser(id, dto);

        UserResponseDTO response = new UserResponseDTO(user.getName(), user.getEmail());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.softDeleteUser(id);
    }
}
