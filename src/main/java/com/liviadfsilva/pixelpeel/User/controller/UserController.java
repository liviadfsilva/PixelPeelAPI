package com.liviadfsilva.pixelpeel.User.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<UserResponseDTO> users = service.getAllUsers()
            .stream()
            .map(user -> new UserResponseDTO(user.getName(), user.getEmail()))
            .toList();

        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        return service.getUserById(id)
                .map(user -> new UserResponseDTO(user.getName(), user.getEmail()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {

        return service.getUserByEmail(email)
            .map(user -> new UserResponseDTO(user.getName(), user.getEmail()))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponseDTO>> getUserByRole(@PathVariable Role role) {

        List<UserResponseDTO> users = service.getUserByRole(role)
            .stream()
            .map(user -> new UserResponseDTO(user.getName(), user.getEmail()))
            .toList();

        return ResponseEntity.ok(users);
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

    @PatchMapping("/{id}")
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
