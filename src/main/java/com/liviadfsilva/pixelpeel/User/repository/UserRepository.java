package com.liviadfsilva.pixelpeel.User.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liviadfsilva.pixelpeel.User.model.Role;
import com.liviadfsilva.pixelpeel.User.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);

    List<User> findByRole(Role Role);

}
