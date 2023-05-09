package com.example.atrium.repositories;

import com.example.atrium.models.Session;
import com.example.atrium.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    Optional<Session> findByIpAddress(String ipAddress);
    Optional<Session> findByUser(User email);
}
