package com.example.atrium.repositories;

import com.example.atrium.models.User;
import com.example.atrium.models.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPasswordRepository extends JpaRepository<UserPassword, Long> {
    Optional<UserPassword> findByUser(User user);
}
