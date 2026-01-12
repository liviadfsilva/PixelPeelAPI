package com.liviadfsilva.pixelpeel.User.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.liviadfsilva.pixelpeel.Auth.dto.AuthenticatedUserDTO;
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

    private Long getCurrentUserId() {
        AuthenticatedUserDTO user =
            (AuthenticatedUserDTO) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return user.getId();
    }

    public User getUserById(Long id) {
        Long currentUserId = getCurrentUserId();

        if (!currentUserId.equals(id)) {
            throw new AccessDeniedException("You cannot access another user's data.");
        }

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
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

        if (dto.getName() != null) {
        user.setName(dto.getName());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

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