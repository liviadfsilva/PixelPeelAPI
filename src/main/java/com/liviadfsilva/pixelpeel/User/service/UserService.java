package com.liviadfsilva.pixelpeel.User.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.liviadfsilva.pixelpeel.User.dto.UserUpdateDTO;
import com.liviadfsilva.pixelpeel.User.model.Role;
import com.liviadfsilva.pixelpeel.User.model.User;
import com.liviadfsilva.pixelpeel.User.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<User> getUserByRole(Role role) {
        return repository.findByRole(role);
    }

    public User registerUser(String name, String email, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        user.setIsActive(true);
        return repository.save(user);
    }

    public User createAdmin(String name, String email, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRole(Role.ADMIN);
        user.setIsActive(true);
        return repository.save(user);
    }

    public User updateUser(Long id, UserUpdateDTO dto) {
        User user = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found."));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        String newPassword = dto.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            if (passwordEncoder.matches(newPassword, user.getPassword())) {
                throw new RuntimeException("New password must be different from previous password.");
            }

            user.setPassword(passwordEncoder.encode(newPassword));
        }
        
        return repository.save(user);
    }

    public void softDeleteUser(Long id) {
    User user = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found."));

        user.setIsActive(false);
        repository.save(user);
    }
}
