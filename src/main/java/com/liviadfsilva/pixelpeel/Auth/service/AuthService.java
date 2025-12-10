package com.liviadfsilva.pixelpeel.Auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.liviadfsilva.pixelpeel.Auth.jwt.JwtService;
import com.liviadfsilva.pixelpeel.Auth.model.LoginRequest;
import com.liviadfsilva.pixelpeel.User.model.User;
import com.liviadfsilva.pixelpeel.User.repository.UserRepository;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password.");
        }

        return jwtService.generateToken(user.getEmail());
    }
}
